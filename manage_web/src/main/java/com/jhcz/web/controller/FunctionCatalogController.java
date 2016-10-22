package com.jhcz.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhcz.base.domain.ManageCatalog;
import com.jhcz.base.domain.system.Result;
import com.jhcz.web.aspect.MethodLog;
import com.jhcz.web.service.ManageCatalogService;

@Controller
@RequestMapping("/functionCatalog")
public class FunctionCatalogController {
    @Resource
    ManageCatalogService manageCatalogService;

    @ResponseBody
    @RequestMapping("queryFunctionCatalog.action")
    @MethodLog(remark = "根据父栏目id查询子栏目")
    public Result queryFunctionCatalogByParentId(String parentId) {
        Result result = new Result();
        List<ManageCatalog> catalogs = manageCatalogService.queryFunctionCatalogByParentId("1");
        result.setObj(catalogs);
        return result;
    }
}
