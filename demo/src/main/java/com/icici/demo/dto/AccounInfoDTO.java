
import lombok.Data;

@Data
public class AccounInfoDTO {
    Long accountNumber;
    double balance;
    String accountType;
    String accountHolderName;
    String email;
    String phoneNumber;

}