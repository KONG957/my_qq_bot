package love.baihao.service.impl;

import love.baihao.service.ListenerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.service.impl
 * @className: HttpListenerServiceImpl
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 16:57
 * @version: 1.0
 */
@ConditionalOnProperty(name = "listener.http.enable", havingValue = "true",matchIfMissing = false)
@Service
public class HttpListenerServiceImpl implements ListenerService {


}
