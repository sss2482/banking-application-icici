import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import 

@Service
public class AccountsService {
    @Autowired
    AccountsRepository accountsRepository;

    public Page<Accounts> fetchAllAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Accounts> accounts = accountsRepository.findAll(pageable);
        userRepository.findAll().stream().map(user -> {
            List<Accounts> accounts = user.getAccounts();
            return accounts.stream().map(account -> {
                AccountInfoDTO dto = new AccountInfoDTO();
                dto.setAccountNumber(account.getAccountNumber());
                dto.setBalance(account.getBalance());
                dto.setAccountType(account.getAccountType());
                dto.setAccountHolderName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setPhoneNumber(user.getPhoneNumber());
                return dto;
            }).toList();
        }).toList();
    }
}