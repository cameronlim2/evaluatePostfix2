import java.util.*;

public class evaluatePostfix2 
{       
    //************************************* */
    //*****Create global variables needed****/
    //************************************* */
    private static Stack<Double> stack = new Stack<Double>();
    private static Queue<String> queue = new LinkedList<>();
    private static String again = "Yes";
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println();
        System.out.println(about());
        System.out.println("----------------------------");
        while(again.equalsIgnoreCase("Yes"))
        {
        getEquation();
        System.out.println(evaluate());
        System.out.println("Do you have another expression to evaluate? If so, please enter \"Yes\"");
        again = scan.nextLine();
        }
    }

    //**************************************************************** */
    //*****Evalutes the strings by pushing if a number, or two pops*** */
    //*****Uses two pops and does the calculation based on operator*** */
    //*****Finally pushes back onto the stack, and in the end 1 left on stack */
    //***************************************************************** */

    private static double evaluate()
    {
        while(queue.isEmpty()==false)
        {
            
            String top = queue.peek();
            queue.remove();

            if (top.equals("*") || top.equals("/") || top.equals("+") || top.equals("-")) 
            {
                double value1 = stack.pop();
                double value2 = stack.pop();

                switch(top){
                    case "+":
                    stack.push(value1+value2);
                    break;
                    
                    case "-":
                    stack.push(value2-value1);
                    break;

                    case "/":
                    stack.push(value2/value1);
                    break;

                    case "*":
                    stack.push(value1*value2);
                    break;
                }
            }

            else 
            {
                stack.push(ConvertStoD(top));
            }
        }
        return stack.pop();
    }

    //**************************************************************** */
    //********************Adds item to the queue************************/
    //**************************************************************** */
    static void enqueue(String item)
    {
        queue.add(item);
    }

    //**************************************************************** */
    //*****Quick information about me!******************************** */
    //**************************************************************** */

    static String about()
    {
        String aboutme = "Cameron Lim, CSC 130 Lab 1";
        return aboutme;
    }

    //*******************************************************************/
    //*****Converts String to Double if not an operator******************/
    //*******************************************************************/
    static double ConvertStoD(String top) 
    {
        double dee = 0;
        try{
            dee = Double.valueOf(top.trim()).doubleValue();
        } catch (NumberFormatException nfe){}
        return dee;
    }

    //********************************************************************/
    //*****Reads input from Scanner then splits string into tokens********/
    //*****Takes tokens and add's them to queue (Returns queue)***********/
    //********************************************************************/

    public static Queue<String> getEquation()
    {
        System.out.println("Please enter a postfix equation (with spaces between each number and operator): ");
        String equation = scan.nextLine();
        String[] equationTokens = equation.split(" ");
        for (int i = 0; i < equationTokens.length; i++)
        {
            queue.add(equationTokens[i]);
        }
        return queue;
    }
}