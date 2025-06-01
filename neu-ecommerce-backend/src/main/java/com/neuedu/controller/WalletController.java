package com.neuedu.controller;

import com.neuedu.pojo.Result;
import com.neuedu.pojo.Wallet;
import com.neuedu.service.BrandService;
import com.neuedu.service.StoreService;
import com.neuedu.service.WalletService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private StoreService storeService;

    //钱包充值
    @PutMapping("/recharge")
    public Result recharge(BigDecimal mount, String payCode) {
        if (mount.compareTo(BigDecimal.ZERO) < 0) {
            return Result.error("无法充值复数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer role = (Integer) map.get("role");
        Integer id = (Integer) map.get("id");
        if(role == 2) {
            Integer storeId = storeService.findByMerchantsId(id);
            String code = walletService.findCodeByStoreId(storeId);
            if(!code.equals(Md5Util.getMD5String(payCode))) {
                return Result.error("支付密码错误");
            }
            walletService.storeRecharge(mount);
            return Result.success();
        }
        if(role == 3) {
            Integer brandId = brandService.findByBrandsId(id);
            String code = walletService.findCodeByBrandId(brandId);
            if(!code.equals(Md5Util.getMD5String(payCode))) {
                return Result.error("支付密码错误");
            }
            walletService.brandRecharge(mount);
            return Result.success();
        }
        return Result.success();
    }

    //钱包提现
    @PutMapping("/update")
    public Result update(BigDecimal mount, String payCode) {
        if (mount.compareTo(BigDecimal.ZERO) < 0) {
            return Result.error("无法充值复数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer role = (Integer) map.get("role");
        if(role == 2) {
            Integer merchantsId = (Integer) map.get("id");
            Integer storeId = storeService.findByMerchantsId(merchantsId);
            String code = walletService.findCodeByStoreId(storeId);
            if(!code.equals(Md5Util.getMD5String(payCode))) {
                return Result.error("支付密码错误");
            }
            BigDecimal balance = walletService.findBalanceByStoreId(storeId);
            int res = mount.compareTo(balance);
            if(res > 0) {
                return Result.error("别提现了，自己钱包里有多少钱自己心里没点数吗....");
            }
            walletService.updateStore(mount, storeId);
            return Result.success();
        }
        if(role == 3) {
            Integer brandsId = (Integer) map.get("id");
            Integer brandId = brandService.findByBrandsId(brandsId);
            String code = walletService.findCodeByBrandId(brandId);
            if(!code.equals(Md5Util.getMD5String(payCode))) {
                return Result.error("支付密码错误");
            }
            BigDecimal balance = walletService.findBalanceByBrandId(brandId);
            int res = mount.compareTo(balance);
            if(res > 0) {
                return Result.error("别提现了，自己钱包里有多少钱自己心里没点数吗....");
            }
            walletService.updateBrand(mount, brandId);
            return Result.success();
        }
        return Result.success();
    }

    //修改钱包支付密码
    @PutMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer role = (Integer) map.get("role");
        if(role == 2) {
            Integer merchantsId = (Integer) map.get("id");
            Integer storeId = storeService.findByMerchantsId(merchantsId);
            Wallet wallet = walletService.findByStoreId(storeId);
            if(!wallet.getPayCode().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("原密码不正确");
            }
            if(!rePwd.equals(newPwd)) {
                return Result.error("两次输入的新密码不一致");
            }
            walletService.updateStorePwd(newPwd, storeId);
            return Result.success();
        }
        if(role == 3) {
            Integer brandsId = (Integer) map.get("id");
            Integer brandId = brandService.findByBrandsId(brandsId);
            Wallet wallet = walletService.findByBrandId(brandId);
            if(!wallet.getPayCode().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("原密码不正确");
            }
            if(!rePwd.equals(newPwd)) {
                return Result.error("两次输入的新密码不一致");
            }
            walletService.updateBrandPwd(newPwd, brandId);
            return Result.success();
        }
        return Result.success();
    }

    //查询钱包的余额
    @GetMapping("/balance")
    public Result balance() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer role = (Integer) map.get("role");
        if(role == 2) {
            Integer merchantsId = (Integer) map.get("id");
            Integer storeId = storeService.findByMerchantsId(merchantsId);
            Wallet wallet = walletService.findByStoreId(storeId);
            if(wallet == null) {
                return Result.error("该网店还未注册电子钱包");
            }
            return Result.success(wallet.getBalance());
        }
        if(role == 3) {
            Integer brandsId = (Integer) map.get("id");
            Integer brandId = brandService.findByBrandsId(brandsId);
            Wallet wallet = walletService.findByBrandId(brandId);
            if(wallet == null) {
                return Result.error("该网店还未注册电子钱包");
            }
            return Result.success(wallet.getBalance());
        }
        return Result.success();
    }

    //查询自己钱包的接口
    @GetMapping("/findWalletInfo")
    public Result<Wallet> findWalletInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer role = (Integer) map.get("role");
        Wallet wallet = new Wallet();
        if(role == 2) {
            Integer storeId = storeService.findByMerchantsId(id);
            wallet = walletService.findStoreWallet(storeId);
        }
        if(role == 3) {
            Integer brandId = brandService.findByBrandsId(id);
            wallet = walletService.findBrandWallet(brandId);
        }
        return Result.success(wallet);
    }

    //修改钱包的账户名
    @PutMapping("/updateAccount")
    public Result updateAccount(Integer id, String account) {
        walletService.updateAccount(id, account);
        return Result.success();
    }

}
