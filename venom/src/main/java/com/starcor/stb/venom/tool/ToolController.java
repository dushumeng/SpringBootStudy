package com.starcor.stb.venom.tool;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("tool")
public class ToolController {


    @RequestMapping(value = "encrypt", method = {RequestMethod.POST, RequestMethod.GET})
    public String encrypt(HttpServletRequest request, Model model) {
        String beforeValue = request.getParameter("encrypt_before_value");
        if (StringUtils.isNotBlank(beforeValue)) {
            model.addAttribute("encrypt_before_value", beforeValue);
            try {
                byte[] afterValueBytes = AESEncryptHelper.encryptByAes(beforeValue, AESEncryptHelper.password);
                String afterValue = AESEncryptHelper.parseByte2HexStr(afterValueBytes);
                model.addAttribute("encrypt_after_value", afterValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "tool/encrypt";
    }

}
