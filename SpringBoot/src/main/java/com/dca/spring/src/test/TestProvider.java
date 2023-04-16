package com.dca.spring.src.test;

import com.dca.spring.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.dca.spring.config.BaseResponseStatus.*;

@Service
public class TestProvider {

    private final TestDao testDao;

    @Autowired
    public TestProvider(TestDao testDao) {
        this.testDao = testDao;
    }

    public String getUuid() throws BaseException {
        try{
           String uuid = testDao.getUuid();

            return uuid;
        }
        catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
