package io.github.lunkesapps;

import org.hamcrest.Matcher;
import org.junit.runners.model.MultipleFailureException;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static org.hamcrest.MatcherAssert.assertThat;

public class MultiErrorCheck {

    private ArrayList<Throwable> errors = new ArrayList<>();
    private ArrayList<Callable> callables = new ArrayList<>();

    public <T> void addCheck(T actual, Matcher matcher) {
        addCheck("", actual, matcher);
    }

    public <T> void addCheck(String reason, T actual, Matcher matcher) {
        callables.add(new Callable() {
            @Override
            public Object call() throws Exception {
                try {
                    assertThat(reason, actual, matcher);
                } catch (Throwable err) {
                    errors.add(err);
                }
                return null;
            }
        });
    }

    public void validateAllErrors() throws Throwable {
        callables.forEach(callable -> {
            try {
                callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        MultipleFailureException.assertEmpty(errors);
    }
}