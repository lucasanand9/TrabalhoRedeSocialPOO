package telas;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import dados.Postagem;
import negocios.RedeSocial;

public class ImgTable extends AbstractTableModel {
	private final String[] colunas = {"Postagems"};
	private List<Postagem> posts;
	
	public ImgTable(RedeSocial a){
        posts = a.mostrarPostagem();
    }
	
	public int getColumnCount(){
        return 1;
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
		return new ImageIcon(posts.get(rowIndex).getFoto());
	}
}
