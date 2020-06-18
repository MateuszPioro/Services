package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {

     private final static String WELCOME = " Hello ";
     private final static String FALLBACK_ID_WELCOME = " Hola ";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallBackName()  {
        //given
        var mockRepositry = alwaysReturingHelloRepository();
        var SUT = new HelloService(mockRepositry);
        //when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_name_prepareGreeting_returnsGreetingWithName() {
        //given
        var mockRepositry = alwaysReturingHelloRepository();
        var SUT = new HelloService(mockRepositry);
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(WELCOME + " " + name + "!", result);

    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWitFallbackIdLang() {
        //given

        var mockRepository = fallBackLangIdRepository();
        //when
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);

    }
    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWitFallbackIdLang() {
        //given

        var mockRepository = fallBackLangIdRepository();
        //when
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, "abc");
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);

    }
    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWitFallbackIdLang() throws Exception  {
        //given

        var mockRepository = new LangRepository(){
            Optional<Lang> findById(Long id){
                return Optional.empty();
            }
        };
        //when
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting("Andrzej", "4L");
        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " "
                + HelloService.FALLBACK_NAME + "!", result);

    }

    private LangRepository fallBackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturingHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
