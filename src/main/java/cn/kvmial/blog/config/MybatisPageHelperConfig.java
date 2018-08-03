package cn.kvmial.blog.config;

import com.github.pagehelper.PageHelper;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pageHelper的配置类，适用于pageHelper4
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/3 0003 下午 14:40
 */

@Configuration
public class MybatisPageHelperConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
