package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {
    private String category_title;
    private String category_description;

    private int seller_id;
    private String company_name;
    private String seller_name;
    private String email;
    private String phone_number;
    private String address;




}
