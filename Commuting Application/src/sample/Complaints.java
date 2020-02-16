package sample;

public class Complaints {
    String text;
    String name;
    public Complaints(String line)
    {
        String[] arr = new String[3];
        int i = 0;
        for (String str: line.split("=========="))
        {
            arr[i] = str;
            i++;
        }
        name = arr[0];
        text = arr[1];
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String printComplaint()
    {
        String line = getName()+":\n\n"+getText();
        return line;
    }
}
