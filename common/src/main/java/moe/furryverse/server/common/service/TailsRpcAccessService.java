package moe.furryverse.server.common.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import moe.furryverse.server.common.config.TailsRpcConfig;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TailsRpcAccessService {
    static HttpClient client = HttpClient.newHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    final TailsRpcConfig config;

    @SneakyThrows
    public boolean checkTokenAccess(String token, String accountId, List<Access> access) {
        if (access == null) return true;
        if (token == null || accountId == null) return false;

        // 创建请求
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("access=" + mapper.writeValueAsString(access)))
                .uri(URI.create(config.getUrl()));

        // 添加 headers
        Map<String, String> header = Map.of(
                Resource.CustomHeader.RPC_MACHINE_ID_HEADER, config.getId(),
                Resource.CustomHeader.RPC_MACHINE_SIGNATURE_HEADER, "not support",
                Resource.CustomHeader.AUTHORIZE_HEADER, token,
                Resource.CustomHeader.ACCOUNT_ID_HEADER, accountId
        );

        header.forEach(builder::header);

        // 发送请求
        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        // 处理响应
        JavaType type = mapper.getTypeFactory().constructParametricType(Message.class, CheckTokenAccessResponse.class);
        // 获得 Message<checkTokenAccessResponse> 对象
        Message<CheckTokenAccessResponse> message = mapper.readValue(response.body(), type);

        return message.getData().access;
    }

    static class CheckTokenAccessResponse {
        boolean access;
    }
}
