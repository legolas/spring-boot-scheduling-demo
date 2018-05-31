package nl.dulsoft.demo.schedulingjobs;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DemoItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        list.stream().forEach(System.out::println);
    }
}
