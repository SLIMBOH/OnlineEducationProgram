package con.suhao.sms.service;

import java.util.Map;

public interface SmsService {

    boolean send(Map<String, Object> param, String phone);

}
