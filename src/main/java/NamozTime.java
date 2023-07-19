import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NamozTime {

    private String region;
    private String date;
    private String weekday;
    private  Times times;
}
 /*  {
    "region": "Toshkent",
    "date": "2023-07-18",
    "weekday": "Seshanba",
    "times": {
        "tong_saharlik": "03:24",
        "quyosh": "05:05",
        "peshin": "12:29",
        "asr": "17:39",
        "shom_iftor": "19:58",
        "hufton": "21:29"
    }
}*/
