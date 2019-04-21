package sample.Model.Entities;

/**
 * The class that represents the token game item in Hanabi.
 *
 * @version 1.0 16 Mar 2019
 * @author Aeysol Rosaldo
 */
public class Token {

    /* The maximum number of tokens in the game */
    public int max;

    /* The current number of tokens in the game */
    public int current;

    /* The type of the token */
    public TType tType;

    /**
     * Two types of token: Info or Fuse
     */
    public enum TType{
        INFOTOKEN,
        FUSETOKEN
    }

    /**
     * Constructor of the token class
     * @param type the type of token
     */
    public Token(TType type){
        //At the start, 8 information tokens are available
        if(type == TType.INFOTOKEN){
            max = 8;
            current = 8;
            tType = TType.INFOTOKEN;
        }
        else{ //At the start, 4 fuses are given
            max = 3;
            current = 3;
            tType = TType.FUSETOKEN;
        }
    }

    /**
     * Spend info tokens when player gives information about the other players' cards
     * @precond current number of info tokens are greater than 0
     */
    public void spend(){
        if(tType == TType.INFOTOKEN){
            if( current > 0 ){
                current--; //decrement info tokens
            }
            else{ //error message, infotokens = 0
                System.out.println("You have no information tokens left.");
            }
        }
        else{ //error message, token type = fuse token
            System.out.println("You cannot spend fuse tokens.");
        }
    }

    /**
     * Add info tokens when player discards a card
     * @precond current number of tokens are less then 8
     */
    public void add(){
        if(tType == TType.INFOTOKEN){
            if( current < max ){
                current++; //increment info tokens
            }
            else{ //error message, current tokens = 8
                System.out.println("You have 8 information tokens right now.");
            }
        }
        else{ //error message, token type = fuse tokens
            System.out.println("You cannot add fuse tokens");
        }
    }

    public void burn(){
        if(tType == TType.FUSETOKEN){
            if( current > 1 ){
                current--;
            }
            else{
                System.out.println("You're out of fuse tokens. Game over.");
            }
        }
        else{
            System.out.println("You cannot burn information tokens.");
        }
    }

    /**
     * Get the max number of tokens
     * @return max number of tokens
     */
    public int getMax(){
        return max;
    }

    /**
     * Get the current number of tokens
     * @return current number of tokens
     */
    public int getCurrent(){
        return current;
    }

    /**
     * Get the type of token
     * @return type of token
     */
    public TType getType(){
        return tType;
    }

    public static void main(String[] args){

        Token info = new Token(TType.INFOTOKEN);

        Token fuse = new Token(TType.FUSETOKEN);

        System.out.println("Token.java Testing");
        System.out.println();

        if(info.getMax() != 8){
            System.out.println("There should be a maximum of 8 information tokens.");
        }

        info.spend();

        if(info.getCurrent() != 7){
            System.out.println("There should be 7 current information tokens.");
        }

        fuse.spend();
        fuse.add();

        System.out.println("Regression testing done.");

    }


}
