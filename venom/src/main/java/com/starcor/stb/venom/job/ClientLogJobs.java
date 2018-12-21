package com.starcor.stb.venom.job;

import com.starcor.stb.venom.log.Logger;
import com.starcor.stb.venom.upload.ClientLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClientLogJobs {

    @Autowired
    private ClientLogService clientLogService;

    @Scheduled(fixedDelay = 3 * 60 * 1000)
    public void checkFileSize1() {
        Logger.i("---每3分钟检查一次文件大小 checkFileSize1---");
        clientLogService.checkClientLogFileSize();
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void checkFileSize2() {
        Logger.i("---每天1点清理一次文件 checkFileSize2---");
        clientLogService.removeOvertimeClientLog();
    }
}
