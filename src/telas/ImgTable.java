package telas;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import dados.Postagem;
import negocios.RedeSocial;

public class ImgTable extends AbstractTableModel {
private final String[] colunas = {"Postagens", "Legenda"};
	private List<Postagem> posts;
	
	public ImgTable(RedeSocial a){
        posts = a.mostrarPostagem();
    }
	
	public int getColumnCount(){
        return 2;
    }
	
	public String getColumnName(int column){
        return colunas[column];
    }
	
	public int getRowCount(){
        return posts.size();
    }
	
	@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return new ImageIcon(posts.get(rowIndex).getFoto());
		}else {
			return posts.get(rowIndex).getLegenda();
		}
		
	}
}
