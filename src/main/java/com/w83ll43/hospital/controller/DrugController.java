package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Drug;
import com.w83ll43.hospital.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Resource
    private DrugService drugService;

    /**
     * 添加药品
     * @param drug
     * @return
     */
    @PostMapping
    public Result<Drug> addDrug(@RequestBody Drug drug) {
        return null;
    }

    /**
     * 根据 ID 查询药品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Drug> getDrugById(@PathVariable("id") Long id) {
        Drug drug = drugService.getById(id);

        if (drug == null) {
            return Result.error("查询结果为空！");
        }

        return Result.success(drug);
    }

    /**
     * 分页获取药品信息
     * @return
     */
    @GetMapping("/all")
    public Result<List<Drug>> getDrugs(int size, int page) {
        return null;
    }

    /**
     * 修改药品信息
     * @param drug
     * @return
     */
    @PutMapping
    public Result<Drug> updateDrug(@RequestBody Drug drug) {
        return null;
    }

    /**
     * 根据 ID 删除药品
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteDrugById(@PathVariable("id") Long id) {
        return null;
    }

}
