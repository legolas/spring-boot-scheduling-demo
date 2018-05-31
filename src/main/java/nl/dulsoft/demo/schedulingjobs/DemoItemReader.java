package nl.dulsoft.demo.schedulingjobs;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.*;

public class DemoItemReader implements ItemReader<String> {
    private ListIterator<String> iterator;
    private List<String> list = Arrays.asList("This is a demo 1", "This is a demo 2", "This is a demo 3", "This is a demo 4", "This is a demo 5");

    public DemoItemReader() {
        iterator = list.listIterator();
    }
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        boolean hasNext = iterator.hasNext();

        if(hasNext) {
            return iterator.next();
        }

        iterator = list.listIterator();
        return null;
    }
}
