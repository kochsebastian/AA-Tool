package view;
import IView;
import ViewEmpty;
import ViewFull;

public class ViewFactory 
{
	public static IView createView(int iNumber) throws Exception
    {
        switch(iNumber)
        {
            case 0:
                //return new ViewEmpty();
            case 1:
                //return new ViewFull();
            default:
                throw new Exception("Error in ViewFactory");
        }
    }


}
