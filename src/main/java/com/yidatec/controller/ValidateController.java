package com.yidatec.controller;

import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author QuShengWen
 */
@CrossOrigin(origins = "*")
@RestController
public class ValidateController extends BaseController{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping(value = "/validateUserName")
    public String validateName(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入真实姓名");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateMobile")
    public String validateMobilePhone(@RequestParam(value="mobile") String mobile,@RequestParam(value="isEdit")boolean isEdit){
        if(mobile == null || mobile.trim().isEmpty()){
            return getErrorJson("必须输入手机号码");
        }
        mobile = mobile.trim();
        boolean res = mobile.matches("^1[3|4|5|7|8][0-9]\\d{4,8}$");
        if(!res)
            return getErrorJson("手机号码格式不正确");
        else if(!isEdit){
            User user = userMapper.findByMobilePhone(mobile);
            if(user != null)
                return getErrorJson("手机号码已存在");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePassword")
    public String validatePassword(@RequestParam(value="password") String password){
        if(password == null || password.trim().isEmpty()){
            return getErrorJson("必须输入密码");
        }
        password = password.trim();
        boolean res = password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}");
        if(!res)
            return getErrorJson("密码必须是5~10位数字和字母的组合");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateInteger")
    public String validateInteger(@RequestParam(value="integer") String integer){
        if(integer == null)return getSuccessJson(null);

        integer = integer.trim();
        if(integer.isEmpty())return getSuccessJson(null);
        try{
            Integer.parseInt(integer);
        }catch(Exception ex){
            return getErrorJson("必须输入整数");
        }

        return getSuccessJson(null);
    }


    @RequestMapping(value = "/validateFloat")
    public String validateFloat(@RequestParam(value="flo") String flo){
        if(flo == null)return getSuccessJson(null);

        flo = flo.trim();
        if(flo.isEmpty())return getSuccessJson(null);
        try{
            Float.parseFloat(flo);
        }catch(Exception ex){
            return getErrorJson("必须输入整数或小数");
        }

        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateEmail")
    public String validateEmail(@RequestParam(value="email") String email){
        if(email == null)return getSuccessJson(null);
        email = email.trim();
        if(email.isEmpty())return getSuccessJson(null);
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        if(!flag){
            return getErrorJson(" 邮箱格式不正确");
        }

        return getSuccessJson(null);
    }



    @RequestMapping(value = "/validateRoleName")
    public String validateRoleName(@RequestParam(value="name") String name,@RequestParam(value="isEdit")boolean isEdit){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入角色名称");
        }

        if(!isEdit){
            Role role = roleMapper.findByName(name);
            if(role != null)
                return getErrorJson("角色名称已存在");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCode")
    public String validateCode(@RequestParam(value="code") String code){
        if(code == null || code.trim().isEmpty()){
            return getErrorJson("必须输入主键");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateSort")
    public String validateSort(@RequestParam(value="sort") String sort){
        if(sort == null || sort.trim().isEmpty()){
            return getErrorJson("必须输入序号");
        }
        sort = sort.trim();
        boolean res = sort.matches( "^[0-9]*[0-9][0-9]*$");
        if(!res)
            return getErrorJson("序号必须是数字");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateValue")
    public String validateValue(@RequestParam(value="value") String value){
        if(value == null || value.trim().isEmpty()){
            return getErrorJson("必须输入项目值");
        }
        return getSuccessJson(null);
    }
    @RequestMapping(value = "/validateDescription")
    public String validateDescription(@RequestParam(value="description") String description){
        if(description == null || description.trim().isEmpty()){
            return getErrorJson("必须输入描述");
        }
        return getSuccessJson(null);
    }
    @RequestMapping(value = "/validateChannel")
    public String validateChannel(@RequestParam(value="channel") String channel){
        if(channel == null || channel.trim().isEmpty()){
            return getErrorJson("必须选择区域");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCompanyName")
    public String validateCompanyName(@RequestParam(value="name") String companyName){
        if(companyName == null || companyName.trim().isEmpty()){
            return getErrorJson("必须输入企业名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDirector")
    public String validateDirector(@RequestParam(value = "director") String director) {
        if (director == null || director.trim().isEmpty()) {
            return getErrorJson("必须输入工厂厂长名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateIndustry")
    public String validateIndustry(@RequestParam(value="industry") String industry){
        if(industry == null || industry.trim().isEmpty()){
            return getErrorJson("必须选择所属行业");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateNature")
    public String validateNature(@RequestParam(value="nature") String nature){
        if(nature == null || nature.trim().isEmpty()){
            return getErrorJson("必须选择企业性质");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCountry")
    public String validateCountry(@RequestParam(value="country") String country){
        if("未选择".equals(country)){
            return getErrorJson("必须选择国家");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateProvince")
    public String validateProvince(@RequestParam(value="province") String province){
        if("未选择".equals(province) ){
            return getErrorJson("必须选择省份");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCity")
    public String validateCity(@RequestParam(value="city") String city){
        if("未选择".equals(city)){
            return getErrorJson("必须选择城市");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateRegion")
    public String validateRegion(@RequestParam(value="region") String region){
        if("未选择".equals(region)){
            return getErrorJson("必须选择区域");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateAddress")
    public String validateAddress(@RequestParam(value="address") String address){
        if(address == null || address.trim().isEmpty()){
            return getErrorJson("必须输入地址");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateLevel")
    public String validateLevel(@RequestParam(value="level") String level){
        if(level == null || level.trim().isEmpty()){
            return getErrorJson("必须选择平台等级");
        }
        return getSuccessJson(null);
    }


    @RequestMapping(value = "/validateReferrer")
    public String validateReferrer(@RequestParam(value="referrer") String referrer){
        if(referrer == null || referrer.trim().isEmpty()){
            return getErrorJson("必须输入推荐人");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateEnglishAbility")
    public String validateEnglishAbility(@RequestParam(value="englishAbility") String englishAbility){
        if(englishAbility == null || englishAbility.trim().isEmpty()){
            return getErrorJson("必须输入英文能力");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePrevious")
    public String validatePrevious(@RequestParam(value="previous") String previous){
        if(previous == null || previous.trim().isEmpty()){
            return getErrorJson("必须输入最近上家公司");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateExperience")
    public String validateExperience(@RequestParam(value="experience") String experience){
        if(experience == null || experience.trim().isEmpty()){
            return getErrorJson("必须输入从业年限");
        }
        experience = experience.trim();
        boolean res = experience.matches( "^[0-9]*[0-9][0-9]*$");
        if(!res)
            return getErrorJson("必须输入大于等于0的整数");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateGoodAtIndustry")
    public String validateGoodAtIndustry(@RequestParam(value="goodAtIndustry") String goodAtIndustry){
        if(goodAtIndustry == null || goodAtIndustry.trim().isEmpty()){
            return getErrorJson("必须输入擅长行业");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateGoodAtMaterial")
    public String validateGoodAtMaterial(@RequestParam(value="goodAtMaterial") String goodAtMaterial){
        if(goodAtMaterial == null || goodAtMaterial.trim().isEmpty()){
            return getErrorJson("必须输入擅长材料");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateGoodAtArea")
    public String validateGoodAtArea(@RequestParam(value="goodAtArea") String goodAtArea){
        if(goodAtArea == null || goodAtArea.trim().isEmpty()){
            return getErrorJson("必须输入擅长面积");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCategory")
    public String validateCategory(@RequestParam(value="category") String category){
        if(category == null || category.trim().isEmpty()){
            return getErrorJson("必须输入产品分类");
        }
        return getSuccessJson(null);
    }
    @RequestMapping(value = "/validatePDName")
    public String validatePDName(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入产名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDesc")
    public String validateDesc(@RequestParam(value="desc") String desc){
        if(desc == null || desc.trim().isEmpty()){
            return getErrorJson("必须输入细节描述");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateUnit")
    public String validateUnit(@RequestParam(value="unit") String unit){
        if(unit == null || unit.trim().isEmpty()){
            return getErrorJson("必须输入单位");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateWay")
    public String validateWay(@RequestParam(value="way") String way){
        if(way == null || way.trim().isEmpty()){
            return getErrorJson("必须输入方式");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateLow")
    public String validateLow(@RequestParam(value="low") String low){
        if(low == null || low.trim().isEmpty()){
            return getErrorJson("必须输入低价");
        }

        boolean res2 = low.matches("^[0-9]+(.[0-9]{2})?$");
        if(!res2)
            return getErrorJson("低价整数位不能超过18位,小数位必须是两位");

        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateMiddle")
    public String validateMiddle(@RequestParam(value="middle") String middle){
        if(middle == null || middle.trim().isEmpty()){
            return getErrorJson("必须输入中价");
        }
        boolean res2 = middle.matches("^[0-9]+(.[0-9]{2})?$");
        if(!res2)
            return getErrorJson("中价整数位不能超过18位,小数位必须是两位");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateHigh")
    public String validateHigh(@RequestParam(value="high") String high){
        if(high == null || high.trim().isEmpty()){
            return getErrorJson("必须输入高价");
        }
        boolean res2 = high.matches("^[0-9]+(.[0-9]{2})?$");
        if(!res2)
        if(!res2)
            return getErrorJson("高价整数位不能超过18位,小数位必须是两位");
        return getSuccessJson(null);
    }
    @RequestMapping(value = "/validateUsername")
    public String validateUsername(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入联系人姓名");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateExhibitionName")
    public String validateExhibitionName(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入展馆名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateStartTime")
    public String validateStartTime(@RequestParam(value="startTime") String startTime){
        if(startTime == null || startTime.trim().isEmpty()){
            return getErrorJson("必须输入开展时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateHobby")
    public String validateHobby(@RequestParam(value="hobby") String hobby){
        if(hobby == null || hobby.trim().isEmpty()){
            return getErrorJson("必须输入兴趣爱好");
        }
        return getSuccessJson(null);
    }


    @RequestMapping(value = "/validateCampaignName")
    public String validateCampaignName(@RequestParam(value="campaignName") String campaignName){
        if(campaignName == null || campaignName.trim().isEmpty()){
            return getErrorJson("必须输入参加的活动");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDesignStyle")
    public String validateDesignStyle(@RequestParam(value="designStyle") String designStyle){
        if(designStyle == null || designStyle.trim().isEmpty()){
            return getErrorJson("必须输入设计风格");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePlatformLevel")
    public String validatePlatformLevel(@RequestParam(value="platformLevel") String platformLevel){
        if(platformLevel == null || platformLevel.trim().isEmpty()){
            return getErrorJson("必须输入平台级别");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateSignDay")
    public String validateSignDay(@RequestParam(value="signDay") String signDay){
        if(signDay == null || signDay.trim().isEmpty()){
            return getErrorJson("必须输入合同签署日期");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateContractName")
    public String validateContractName(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入合同名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateContractCode")
    public String validateContractCode(@RequestParam(value="code") String code){
        if(code == null || code.trim().isEmpty()){
            return getErrorJson("必须输入合同编码");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateProjectId")
    public String validateProjectId(@RequestParam(value="projectId") String projectId){
        if(projectId == null || projectId.trim().isEmpty()){
            return getErrorJson("必须输入项目编码");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateProjectName")
    public String validateProjectName(@RequestParam(value="projectName") String projectName){
        if(projectName == null || projectName.trim().isEmpty()){
            return getErrorJson("必须输入项目名称");
        }
        return getSuccessJson(null);
    }



    @RequestMapping(value = "/validateCampaignId")
    public String validateCampaignId(@RequestParam(value="campaignId") String campaignId){
        if(campaignId == null || campaignId.trim().isEmpty()){
            return getErrorJson("必须输入市场活动");
        }
        return getSuccessJson(null);
    }


    @RequestMapping(value = "/validateExhibitionNumber")
    public String validateExhibitionNumber(@RequestParam(value="exhibitionNumber") String exhibitionNumber){
        if(exhibitionNumber == null || exhibitionNumber.trim().isEmpty()){
            return getErrorJson("必须输入展位号");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateArea")
    public String validateArea(@RequestParam(value="area") String area){
        if(area == null || area.trim().isEmpty()){
            return getErrorJson("必须输入面积");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatAemount")
    public String validatAemount(@RequestParam(value="amount") String amount){
        if(amount == null || amount.trim().isEmpty()){
            return getErrorJson("必须输入合同总价");
        }
        boolean res2 = amount.matches("^[0-9]+(.[0-9]{2})?$");
        if(!res2)
            if(!res2)
                return getErrorJson("合同总价整数位不能超过18位,小数位必须是两位");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatCategory")
    public String validatCategory(@RequestParam(value="category") String category){
        if(category == null || category.trim().isEmpty()){
            return getErrorJson("必须输入合同分类");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatTax")
    public String validatTax(@RequestParam(value="tax") String tax){
        if(tax == null || tax.trim().isEmpty()){
            return getErrorJson("必须输入税");
        }
        boolean res2 = tax.matches("^[0-9]+(.[0-9]{2})?$");
        if(!res2)
            if(!res2)
                return getErrorJson("税整数位不能超过18位,小数位必须是两位");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatContractPaymentMethod")
    public String validatContractPaymentMethod(@RequestParam(value="contractPaymentMethod") String contractPaymentMethod){
        if(contractPaymentMethod == null || contractPaymentMethod.trim().isEmpty()){
            return getErrorJson("必须输入付款方式");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatInitialPaymentTime")
    public String validatInitialPaymentTime(@RequestParam(value="initialPaymentTime") String initialPaymentTime){
        if(initialPaymentTime == null || initialPaymentTime.trim().isEmpty()){
            return getErrorJson("必须输入首付时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatMiddlePaymentTime")
    public String validatMiddlePaymentTime(@RequestParam(value="middlePaymentTime") String middlePaymentTime){
        if(middlePaymentTime == null || middlePaymentTime.trim().isEmpty()){
            return getErrorJson("必须输入中款时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatFinalPaymentTime")
    public String validatFinalPaymentTime(@RequestParam(value="finalPaymentTime") String finalPaymentTime){
        if(finalPaymentTime == null || finalPaymentTime.trim().isEmpty()){
            return getErrorJson("必须输入尾款时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatBillingInfo")
    public String validatBillingInfo(@RequestParam(value="billingInfo") String billingInfo){
        if(billingInfo == null || billingInfo.trim().isEmpty()){
            return getErrorJson("必须输入开票信息");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatFirstParty")
    public String validatFirstParty(@RequestParam(value="firstParty") String firstParty){
        if(firstParty == null || firstParty.trim().isEmpty()){
            return getErrorJson("必须输入合同甲方");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatSecondParty")
    public String validatSecondParty(@RequestParam(value="secondParty") String secondParty){
        if(secondParty == null || secondParty.trim().isEmpty()){
            return getErrorJson("必须输入合同乙方");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatMoneyType")
    public String validatMoneyType(@RequestParam(value="moneyType") String moneyType){
        if(moneyType == null || moneyType.trim().isEmpty()){
            return getErrorJson("必须输入款项类型");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatPaymentMethod")
    public String validatPaymentMethod(@RequestParam(value="paymentMethod") String paymentMethod){
        if(paymentMethod == null || paymentMethod.trim().isEmpty()){
            return getErrorJson("必须输入支付方式");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatCostCenter")
    public String validatCostCenter(@RequestParam(value="costCenter") String costCenter){
        if(costCenter == null || costCenter.trim().isEmpty()){
            return getErrorJson("必须输入成本中心");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatPaymentTime")
    public String validatPaymentTime(@RequestParam(value="paymentTime") String paymentTime){
        if(paymentTime == null || paymentTime.trim().isEmpty()){
            return getErrorJson("必须输入付款时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatPaymentAmount")
    public String validatPaymentAmount(@RequestParam(value="paymentAmount") String paymentAmount){
        if(paymentAmount == null || paymentAmount.trim().isEmpty()){
            return getErrorJson("必须输入付款金额");
        }
        boolean res2 = paymentAmount.matches("^-?[0-9]+(.[0-9]{2})?$");
        if(!res2)
            if(!res2)
                return getErrorJson("付款金额整数位不能超过18位,小数位必须是两位");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatPerator")
    public String validatPerator(@RequestParam(value="operator") String operator){
        if(operator == null || operator.trim().isEmpty()){
            return getErrorJson("必须输入经办人");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatReasonForChange")
    public String validatReasonForChange(@RequestParam(value="reasonForChange") String reasonForChange){
        if(reasonForChange == null || reasonForChange.trim().isEmpty()){
            return getErrorJson("必须输入变更原因");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatLedgerInput")
    public String validatLedgerInput(@RequestParam(value="ledgerInput") String ledgerInput){
        if(ledgerInput == null || ledgerInput.trim().isEmpty()){
            return getErrorJson("必须输入台账");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateType")
    public String validateType(@RequestParam(value = "type") String type) {
        if (type == null || type.trim().isEmpty()) {
            return getErrorJson("必须选择类型");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCustomerId")
    public String validateCustomerId(@RequestParam(value = "customerId") String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return getErrorJson("必须选择客户");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateBudget")
    public String validateBudget(@RequestParam(value = "budget") String budget) {
        if (budget == null || budget.trim().isEmpty()) {
            return getErrorJson("必须输入预算");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateSetupTime")
    public String validateSetupTime(@RequestParam(value = "setupTime") String setupTime) {
        if (setupTime == null || setupTime.trim().isEmpty()) {
            return getErrorJson("必须输入搭建时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateTearDownTime")
    public String validateTearDownTime(@RequestParam(value = "tearDownTime") String tearDownTime) {
        if (tearDownTime == null || tearDownTime.trim().isEmpty()) {
            return getErrorJson("必须输入撤展时间");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCost")
    public String validateCost(@RequestParam(value = "cost") String cost) {
        if (cost == null || cost.trim().isEmpty()) {
            return getErrorJson("必须输入成本");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDegreeOfImportance")
    public String validateDegreeOfImportance(@RequestParam(value = "degreeOfImportance") String degreeOfImportance) {
        if (degreeOfImportance == null || degreeOfImportance.trim().isEmpty()) {
            return getErrorJson("必须选择重要程度");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePotential")
    public String validatePotential(@RequestParam(value = "potential") String potential) {
        if (potential == null || potential.trim().isEmpty()) {
            return getErrorJson("必须选择项目潜力");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDesignerId")
    public String validateDesignerId(@RequestParam(value = "designerId") String designerId) {
        if (designerId == null || designerId.trim().isEmpty()) {
            return getErrorJson("必须选择设计师");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateFactoryId")
    public String validateFactoryId(@RequestParam(value = "factoryId") String factoryId) {
        if (factoryId == null || factoryId.trim().isEmpty()) {
            return getErrorJson("必须选择工厂");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatemId")
    public String validatemId(@RequestParam(value = "pmId") String pmId) {
        if (pmId == null || pmId.trim().isEmpty()) {
            return getErrorJson("必须选择项目经理");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateDevelopSaleId")
    public String validateDevelopSaleId(@RequestParam(value = "developSaleId") String developSaleId) {
        if (developSaleId == null || developSaleId.trim().isEmpty()) {
            return getErrorJson("必须选择开发销售");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCaseName")
    public String validateCaseName(@RequestParam(value = "caseName") String caseName) {
        if (caseName == null || caseName.trim().isEmpty()) {
            return getErrorJson("必须输入案例名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePhoto")
    public String validatePhoto(@RequestParam(value = "photo") String photo) {
        if (photo == null || photo.trim().isEmpty()) {
            return getErrorJson("必须上传照片");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCasePhoto")
    public String validateCasePhoto(@RequestParam(value = "casePhoto") String casePhoto) {
        if (casePhoto == null || casePhoto.trim().isEmpty()) {
            return getErrorJson("必须上传照片");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateTaxpayerType")
    public String validateTaxpayerType(@RequestParam(value = "taxpayerType") String taxpayerType) {
        if (taxpayerType == null || taxpayerType.trim().isEmpty()) {
            return getErrorJson("必须选择纳税人身份");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateActivityName")
    public String validateActivityName(@RequestParam(value = "name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return getErrorJson("必须输入活动名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateChooseExhibition")
    public String validateChooseExhibition(@RequestParam(value="exhibitioHall") String exhibitioHall){
        if(exhibitioHall == null || exhibitioHall.trim().isEmpty()){
            return getErrorJson("必须选择展馆名称");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateSponsor")
    public String validateSponsor(@RequestParam(value="sponsor") String sponsor){
        if(sponsor == null || sponsor.trim().isEmpty()){
            return getErrorJson("必须选择主办方");
        }
        return getSuccessJson(null);
    }
}
