package bg.softuni.jsonprocessing.domain.dtos.categories;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImportCategoryDto {
    @Expose
    private String name;

    public ImportCategoryDto(String name) {
        this.name = name;
        validate();
    }
    private void validate(){
        if (name.length() < 3 || name.length() > 15){
            throw new IllegalArgumentException
                    ("The category name should be from 3 to 15 characters.");
        }
    }
}
