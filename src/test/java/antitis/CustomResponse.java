package antitis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {
    private String category_title;
    private String category_description;
    List<CustomResponse> responses;

    private int seller_id;
    private String company_name;
    private String seller_name;
    private String email;
    private String phone_number;
    private String address;
    private String product_title;
    private int category_id;




}
