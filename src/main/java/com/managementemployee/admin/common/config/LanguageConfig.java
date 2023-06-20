package com.managementemployee.admin.common.config;

import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Configuration
public class LanguageConfig {

    public static final List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("vi")

    );
}
