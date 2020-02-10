class HvitRute extends Rute{

  public HvitRute(int k, int r, Rute[][] board){
    super(k,r,board); // sender 4 argumenter i super classe konstruktor
  }

  @Override
  public char tilTegn(){
    return ' ';
  }

  @Override
  public boolean erHvit(){
    return true;
  }

}
