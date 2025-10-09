//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    String internalString = "welcome to java";
    System.out.println("Enter The char :");
    // iput from user ""
    Scanner scan = new Scanner(System.in);
    String stringByUser=scan.nextLine();
    int counter =0;
    for (int i=0;i<internalString.length();i++){
        if(internalString.charAt(i)==stringByUser.charAt(0))
            counter++;
    }

    System.out.println("The character "+stringByUser.charAt(0)+" count is "+counter);



}
