package nl.dulsoft.demo.schedulingjobs;

import org.springframework.batch.item.ItemProcessor;

public class DemoItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return new StringBuilder(item).reverse().toString();
    }
}
