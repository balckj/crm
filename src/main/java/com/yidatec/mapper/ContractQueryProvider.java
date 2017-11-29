package com.yidatec.mapper;

import com.yidatec.model.ProjectEntity;
import com.yidatec.vo.ABVO;
import com.yidatec.vo.ContractVO;
import org.springframework.util.StringUtils;

public class ContractQueryProvider {

    /**
     * 查询根据项目甲方乙方select
     * @param projectEntity
     * @return
     */
    public String getABList(final ProjectEntity projectEntity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id,name FROM T_CUSTOMER A ");
        sb.append(" WHERE A.id =#{customerId}");// 客户

        sb.append(" UNION");

        sb.append(" SELECT id,name FROM T_CAMPAIGN A ");
        sb.append(" WHERE A.id =#{campaignId}");// 活动

        sb.append(" UNION");

        sb.append(" SELECT C.id,C.name FROM T_PROJECT  A LEFT JOIN T_PROJECT_FACTORY B ON A.id=B.projectId");
        sb.append(" LEFT JOIN T_FACTORY C ON B.factoryId = C.id");
        sb.append(" WHERE A.id =#{id}"); // 工厂

        sb.append(" UNION");

        sb.append(" SELECT C.id,C.name FROM T_PROJECT  A ");
        sb.append(" LEFT JOIN T_PROJECT_DESIGNER B ON A.id=B.projectId ");
        sb.append(" LEFT JOIN T_USER C ON B.designerId = C.id ");
        sb.append(" WHERE A.id =#{id}"); // 设计师

        sb.append(" UNION");

        sb.append(" SELECT id,name FROM T_USER A");
        sb.append(" WHERE A.id =#{pmId}"); // 项目经理

        sb.append(" UNION");

        sb.append(" SELECT id,name FROM T_USER A");
        sb.append(" WHERE A.id =#{developSaleId}"); // 开发销售

        if(!StringUtils.isEmpty(projectEntity.getTraceSaleId())){
            sb.append(" UNION");
            sb.append(" SELECT id,name FROM T_USER A");
            sb.append(" WHERE A.id =#{traceSaleId}"); // 跟进销售
        }

        return sb.toString();
    }

    /**
     * 合同列表页table
     * @param contractVO
     * @return
     */
    public String selectContractList(final ContractVO contractVO){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ( ");
        sb.append(" SELECT A.*,B.code AS projectCode ,B.name AS projectName,C.name AS campaignName,count(cl.contractId) AS ledgerCount,B.developSaleId,B.traceSaleId,B.pmId,B.designProgress")
        .append(" FROM T_CONTRACT A")
        .append(" LEFT JOIN T_PROJECT B ON A.projectId = B.id")
        .append(" LEFT JOIN T_CAMPAIGN AS C ON A.campaignId = C.id")
        .append(" LEFT JOIN T_CONTRACT_LEDGER AS cl ON A.id = cl.contractId")
        .append(" GROUP BY A.id");

        sb.append(" ) A ");
        sb.append(" WHERE 1=1 ");

        if(!StringUtils.isEmpty(contractVO.getName())){
            sb.append(" AND A.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(contractVO.getSignDay())){
            sb.append(" AND A.signDay = #{signDay}");
        }
        if(!StringUtils.isEmpty(contractVO.getCampaignName())){
            sb.append(" AND A.campaignName LIKE CONCAT('%',#{campaignName},'%')");
        }
        if(!StringUtils.isEmpty(contractVO.getCategory())){
            sb.append(" AND A.category = #{category}");
        }
        if(!StringUtils.isEmpty(contractVO.getFirstParty())){
            sb.append(" AND A.firstParty = #{firstParty}");
        }
        if(!StringUtils.isEmpty(contractVO.getSecondParty())){
            sb.append(" AND A.secondParty = #{secondParty}");
        }

        if(contractVO.getIsAll() == 0){
            sb.append(" AND (");
            sb.append(" A.pmId = #{id}");
            sb.append(" or A.developSaleId = #{id}");
            sb.append(" or A.traceSaleId = #{id}");
            sb.append(" or A.creatorId = #{id}");
            sb.append(" OR #{id} in (SELECT pd.designerId FROM T_PROJECT p LEFT JOIN T_PROJECT_DESIGNER pd on p.id = pd.projectid where A.id = p.id)");
            sb.append(" )");
        }


        sb.append(" ORDER BY A.modifyTime DESC LIMIT #{start},#{length}");
        return sb.toString();
    }
    /**
     * 合同列表页table
     * @param contractVO
     * @return
     */
    public String countContractList(final ContractVO contractVO){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM (");
        sb.append(" SELECT A.*,B.code AS projectCode ,B.name AS projectName,C.name AS campaignName,count(cl.contractId) AS ledgerCount,B.developSaleId,B.traceSaleId,B.pmId,B.designProgress")
                .append(" FROM T_CONTRACT A")
                .append(" LEFT JOIN T_PROJECT B ON A.projectId = B.id")
                .append(" LEFT JOIN T_CAMPAIGN AS C ON A.campaignId = C.id")
                .append(" LEFT JOIN T_CONTRACT_LEDGER AS cl ON A.id = cl.contractId")
                .append(" GROUP BY A.id");



        sb.append(" ) A");
        sb.append(" WHERE 1=1 ");
        if(!StringUtils.isEmpty(contractVO.getName())){
            sb.append(" AND A.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(contractVO.getSignDay())){
            sb.append(" AND A.signDay = #{signDay}");
        }
        if(!StringUtils.isEmpty(contractVO.getCampaignName())){
            sb.append(" AND A.campaignName LIKE CONCAT('%',#{campaignName},'%')");
        }
        if(!StringUtils.isEmpty(contractVO.getCategory())){
            sb.append(" AND A.category = #{category}");
        }
        if(!StringUtils.isEmpty(contractVO.getFirstParty())){
            sb.append(" AND A.firstParty = #{firstParty}");
        }
        if(!StringUtils.isEmpty(contractVO.getSecondParty())){
            sb.append(" AND A.secondParty = #{secondParty}");
        }

        if(contractVO.getIsAll() == 0){
            sb.append(" AND (");
            sb.append(" A.pmId = #{id}");
            sb.append(" or A.developSaleId = #{id}");
            sb.append(" or A.traceSaleId = #{id}");
            sb.append(" or A.creatorId = #{id}");
            sb.append(" OR #{id} in (SELECT pd.designerId FROM T_PROJECT p LEFT JOIN T_PROJECT_DESIGNER pd on p.id = pd.projectid where A.id = p.id)");

            sb.append(" )");
        }
        return sb.toString();
    }

    /**
     * 合同列表页甲乙合同查询条件table
     * @param abvo
     * @return
     */
    public String selectABVOTable(final ABVO abvo){
        StringBuffer sb = new StringBuffer();
        if ("C".equals(abvo.getType())){
            sb.append("SELECT * FROM T_CUSTOMER A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
            sb.append(" ORDER BY modifyTime DESC LIMIT #{start},#{length}");
        }else if ("F".equals(abvo.getType())){
            sb.append("SELECT * FROM T_FACTORY A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
            sb.append(" ORDER BY modifyTime DESC LIMIT #{start},#{length}");
        }else if ("U".equals(abvo.getType())){
            sb.append("SELECT * FROM T_USER A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
            sb.append(" ORDER BY modifyTime DESC LIMIT #{start},#{length}");
        }
        return sb.toString();
    }

    public String countABVOTable(final ABVO abvo){
        StringBuffer sb = new StringBuffer();
        if ("C".equals(abvo.getType())){
            sb.append("SELECT count(*) FROM T_CUSTOMER A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
        }else if ("F".equals(abvo.getType())){
            sb.append("SELECT count(*) FROM T_FACTORY A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
        }else if ("U".equals(abvo.getType())){
            sb.append("SELECT count(*) FROM T_USER A");
            sb.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(abvo.getSearch())){
                sb.append(" AND A.name LIKE CONCAT('%',#{search},'%')");
            }
        }
        return sb.toString();
    }
}
