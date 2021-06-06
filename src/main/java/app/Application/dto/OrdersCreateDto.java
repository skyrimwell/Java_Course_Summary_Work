package app.Application.dto;
import app.Application.Classes.Orders;
import app.Application.Classes.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersCreateDto {
    private Users users;

    private String date;

    private Long amount;

    private String cardId;

    private String cardType;

    private String cardDate;

    public Orders toEntity(){
        return Orders.builder()
                .users(users)
                .date(date)
                .amount(amount)
                .cardId(cardId)
                .cardType(cardType)
                .cardDate(cardDate)
                .build();
    }
}
