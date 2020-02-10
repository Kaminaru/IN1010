class SortRute extends Rute{

  public SortRute(int k, int r, Rute[][] board){
    super(k,r,board); // sender 3 argumenter i super classe konstruktor
  }

  @Override
  public char tilTegn(){
    return '#';
  }

}
