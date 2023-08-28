package moe.furryverse.server.common.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import moe.furryverse.server.common.message.Message;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Data
@AllArgsConstructor
public class HttpRpcClient {
    static HttpClient client = HttpClient.newHttpClient();
    static ObjectMapper mapper = new ObjectMapper();
    String url;
    String machineId;
    String machineSecret;
    Map<String, String> headers;
    Map<String, String> params;

    @SneakyThrows
    public <T> Message<T> request(Class<T> response) {
        // 构造表单
        StringBuilder strings = new StringBuilder();
        // 拼接数据
        params.forEach((k, v) -> strings.append(k).append("=").append(v).append("&"));

        // 创建请求
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(strings.toString()))
                .uri(URI.create(url));

        // 添加 headers
        headers.forEach(builder::header);

        // 发送请求
        HttpResponse<String> res = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        // 处理响应
        // 先解析状态码
        JavaType message = mapper.getTypeFactory().constructParametricType(Message.class, response);
        return mapper.readValue(res.body(), message);
    }
}
