package com.cgfy.mybatis.base.controller;

import com.cgfy.mybatis.base.bean.AjaxResponse;
import com.cgfy.mybatis.base.util.BaseCommonUtil;
import com.cgfy.mybatis.base.util.BaseConstant;
import com.cgfy.mybatis.base.util.CodeListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(value = "工具控制器，完成通用的一些接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("UtilController")
@RequestMapping("/util")
public class UtilController {


    @ApiOperation(value = "获取UUID", hidden = false)
    @GetMapping("/getUuid")
    public AjaxResponse<String> getUuid() {
        return AjaxResponse.success(BaseCommonUtil.getUUID());
    }
    @ApiOperation(value = "根据数据字典类型获取对应的列表(码表)", hidden = false)
    @GetMapping("/getKeyValueByCode")
    public AjaxResponse<List<Map<String,Object>>> getKeyValueByCode(@ApiParam(name = "codeType", value = "数据字典code类型", required = true) @RequestParam(value = "codeType", required = true) String codeType) {
        return AjaxResponse.success(CodeListUtil.getCodeList(codeType, BaseConstant.getRecordDefaultActiveStatus()));
    }

    //https://mybatis.org/spring/zh/factorybean.html
    @Resource
    private SqlSessionFactoryBean sqlSessionFactory;

    /**
     * add 20211025 by liuyandeng
     * @param sqlMid 接口名称
     * @return
     * @throws Exception
     */
    @GetMapping("/getListBySQLMid")
    public AjaxResponse getListBySQLMid(String sqlMid) throws Exception {
        SqlSession sqlSession =  sqlSessionFactory.getObject().openSession();
        List list = sqlSession.selectList(sqlMid);//接口名字要唯一,就可以不写全空间名
        return AjaxResponse.success(list);
    }


}
