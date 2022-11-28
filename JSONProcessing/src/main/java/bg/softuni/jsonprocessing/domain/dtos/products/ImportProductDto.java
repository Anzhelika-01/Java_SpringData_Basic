package bg.softuni.jsonprocessing.domain.dtos.products;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImportProductDto {
    @Expose
    private String name;
    @Expose
    private Float price;

    public ImportProductDto(String name, Float price) {
        this.name = name;
        this.price = price;
        validate();
    }

    private void validate(){
        if (name.length() < 3){
            throw new IllegalArgumentException
                    ("The name should be at least 3 characters.");
        }
    }
}