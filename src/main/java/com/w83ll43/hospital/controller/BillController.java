package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Bill;
import com.w83ll43.hospital.service.BillService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Resource
    private BillService billService;

    @PostMapping("/hospitalization")
    public Result createHospitalizationBill(@RequestBody Bill bill) {
        bill.setPatientId(BaseContext.getCurrentId());
        bill.setType(3);
        bill.setStatus(0);
        bill.setDate(DateUtils.getCurrentDate());
        boolean result = billService.save(bill);
        return result ? Result.success("创建成功！请前往支付！") : Result.error("创建失败！");
    }

    @PutMapping("/{id}")
    public Result payBillById(@PathVariable("id") Long id) {
        Boolean result = billService.payBill(id);
        return result ? Result.success("缴费成功！") : Result.error("缴费失败！");
    }
}
