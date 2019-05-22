import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPattern {
    public static void compilePatterns() {
        String row = "064b9911-7be2-48cb-8f5c-a7f76d0cc002.16,064b9911-7be2-48cb-8f5c-a7f76d0cc002,16,V5_LaoMao,account.2b156208d99b477c817a4cba75efa029,11,100.000000,556482.250000,423713.218750,150088.000000,TransportAircraft,DummyTransportAircraft_C,0.000000,0.000000,0.000000,0,0.000000,1.000000,33.234000,2018-11-02T07:20:53.560Z";
        String regex = "^(.*)$";
        String[] splitted = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        System.out.println(splitted.length);
        System.out.println(splitted[0]);
        Pattern inputPattern = Pattern.compile(regex, Pattern.DOTALL);

        Matcher m = inputPattern.matcher(row);
        if (!m.matches()) {
            System.out.println("No matches found");
            System.out.println(regex);
            return;
        }


        /*
        for (int i = 0; i < m.groupCount(); i++) {
            String val = m.group(i+1);
            System.out.println(String.format("%d, %s", i + 1, val));
        }
        */
    }

    public static void main(String[] args) throws Exception {
        int count = 100;

        Date begin = new Date();

        for (int i = 0; i < count; i++) {
            compilePatterns();
        }

        Date end = new Date();

        long diff = end.getTime() - begin.getTime();

        long avg = (diff * 1000) / count;

        System.out.println("The avg compile time is");
        System.out.println(avg);
    }
}
