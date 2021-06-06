package app.Application.dto;
import app.Application.Classes.Order;
import app.Application.Classes.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersCreateDto {
    private User users;

    private String date;

    private Long amount;

    private String cardId;

    private String cardType;

    private String cardDate;

    public Order toEntity(){
        return Order.builder()
                .users(users)
                .date(date)
                .amount(amount)
                .cardId(cardId)
                .cardType(cardType)
                .cardDate(cardDate)
                .build();
    }
}
