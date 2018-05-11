package com.example;

import com.example.beans.BackupManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MultipleBeansApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MultipleBeansConfiguration.class);
        BackupManager backupManager = ctx.getBean(BackupManager.class);
        backupManager.backup();
    }

}
