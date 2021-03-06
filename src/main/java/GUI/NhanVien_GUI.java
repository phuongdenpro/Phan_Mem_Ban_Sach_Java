package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.KhachHang;
import entity.NhanVien;
import util.Placeholder;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class NhanVien_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable tblNhanVien;
	private JTextField txtMaNv,txtTenNv,txtSdt,txtDiaChi;
	private DefaultTableModel modelDSNV;
	private List<NhanVien> dsnv;  
	private JButton btnSuaNv,btnXoaNv,btnLamMoi,btnTimKiem;
	private DefaultComboBoxModel cboLoaiTimKiem;
	private JComboBox cmbLoaiTimKiem;
	private JComboBox cboChucVu;
	private JComboBox cboCaLam;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_GUI frame = new NhanVien_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public NhanVien_GUI() throws SQLException {
		setTitle("Nh??n vi??n");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		out = new JPanel();
		out.setLayout(new BoxLayout(out,BoxLayout.Y_AXIS));
		setContentPane(out);
		
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QU???N L?? NH??N VI??N");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		out.add(top);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane,BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		Border compound = BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);
		
		JPanel pnThongTinKh = new JPanel();
		pnLeft.add(pnThongTinKh);
		pnThongTinKh.setLayout(new BoxLayout(pnThongTinKh, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(35);
		pnThongTinKh.add(verticalStrut_2);
		
		JPanel pnTieuDe = new JPanel();
		pnThongTinKh.add(pnTieuDe);
		
		JLabel lblTieuDe = new JLabel("Th??ng tin Nh??n vi??n");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut_1);
		
		JPanel pnMaKh = new JPanel();
		FlowLayout fl_pnMaKh = (FlowLayout) pnMaKh.getLayout();
		fl_pnMaKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnMaKh);
		
		JLabel lblMaKh = new JLabel("M?? NV             ");
		lblMaKh.setPreferredSize(new Dimension(100, 14));
		pnMaKh.add(lblMaKh);
		
		txtMaNv = new JTextField();
		txtMaNv.setEnabled(false);
		txtMaNv.setPreferredSize(new Dimension(7, 30));
		pnMaKh.add(txtMaNv);
		txtMaNv.setColumns(20);
		
		JPanel pnTenKh = new JPanel();
		FlowLayout fl_pnTenKh = (FlowLayout) pnTenKh.getLayout();
		fl_pnTenKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnTenKh);
		
		JLabel lblTenKh = new JLabel("T??n NV");
		lblTenKh.setPreferredSize(new Dimension(100, 14));
		pnTenKh.add(lblTenKh);
		
		txtTenNv = new JTextField();
		txtTenNv.setPreferredSize(new Dimension(7, 30));
		txtTenNv.setColumns(20);
		pnTenKh.add(txtTenNv);
		
		JPanel pnSoDienThoai = new JPanel();
		FlowLayout fl_pnSoDienThoai = (FlowLayout) pnSoDienThoai.getLayout();
		fl_pnSoDienThoai.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnSoDienThoai);
		
		JLabel lblSdt = new JLabel("S??? ??i???n tho???i");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSoDienThoai.add(lblSdt);
		
		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 30));
		txtSdt.setColumns(20);
		pnSoDienThoai.add(txtSdt);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("?????a ch???");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		pnDiaChi.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(7, 30));
		txtDiaChi.setColumns(20);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnCaLam = new JPanel();
		FlowLayout fl_pnCaLam = (FlowLayout) pnCaLam.getLayout();
		fl_pnCaLam.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnCaLam);
		
		JLabel lblCaLam = new JLabel("Ca l??m");
		lblCaLam.setPreferredSize(new Dimension(100, 14));
		pnCaLam.add(lblCaLam);
		
		JPanel pnChucVu = new JPanel();
		FlowLayout fl_pnChucVu = (FlowLayout) pnChucVu.getLayout();
		fl_pnCaLam.setAlignment(FlowLayout.LEFT);
		
		cboCaLam = new JComboBox();
		cboCaLam.setPreferredSize(new Dimension(222, 22));
		pnCaLam.add(cboCaLam);
		pnThongTinKh.add(pnChucVu);
		cboCaLam.addItem((String) "S??ng");
		cboCaLam.addItem((String) "Chi???u");
		
		JLabel lblChucVu = new JLabel("Ch???c v???");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setPreferredSize(new Dimension(100, 14));
		pnChucVu.add(lblChucVu);
		
		cboChucVu = new JComboBox();
		cboChucVu.setPreferredSize(new Dimension(222, 22));
		pnChucVu.add(cboChucVu);
		cboChucVu.addItem((String) "Nh??n vi??n b??n h??ng");
		cboChucVu.addItem((String) "Nh??n vi??n qu???n l?? s???n ph???m");
		cboChucVu.addItem((String) "Ng?????i qu???n l??");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut);
		
		JPanel pnChucNang = new JPanel();
		pnThongTinKh.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnSuaNv = new JButton("S???a");
		btnSuaNv.setBackground(Color.WHITE);
		btnSuaNv.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
//		btnSuaNv.setIconTextGap(30);
		pnChucNang.add(btnSuaNv);
		
		btnXoaNv = new JButton("X??a");
		btnXoaNv.setBackground(Color.WHITE);
		btnXoaNv.setIcon(new ImageIcon("data\\images\\trash.png"));
//		btnXoaNv.setIconTextGap(10);
		pnChucNang.add(btnXoaNv);
		
		btnLamMoi = new JButton("L??m m???i");
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setIcon(new ImageIcon("data\\images\\refresh.png"));
//		btnLamMoi.setIconTextGap(10);
		pnChucNang.add(btnLamMoi);
		
		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("t??m ki???m theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);
		cboLoaiTimKiem.addElement((String) "T??n NV");
		cboLoaiTimKiem.addElement((String) "S??? ??i???n tho???i");
		cboLoaiTimKiem.addElement((String) "?????a ch???");
				
		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		//PromptSupport.setPrompt("nh???p li???u t??m ki???m", txtNhapLieu);
		new Placeholder().placeholder(txtNhapLieu, "nh???p li???u t??m ki???m");
		txtNhapLieu.setColumns(30);
		
		btnTimKiem = new JButton("T??m ki???m");
		btnTimKiem.setPreferredSize(new Dimension(130, 25));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setIcon(new ImageIcon("data\\images\\search_16.png"));
		pnTimKiem.add(btnTimKiem);
		
		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));
		
		String[] cols_dskh = {"M?? nh??n vi??n", "T??n nh??n vi??n", "S??? ??i???n tho???i", "?????a ch???","Ca l??m","ch???c v???","T??i kho???n"};
		modelDSNV = new DefaultTableModel(cols_dskh, 0);
		tblNhanVien = new JTable(modelDSNV);
		JScrollPane scrTableNhanVien = new JScrollPane(tblNhanVien);
		scrTableNhanVien.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableNhanVien.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableNhanVien);
		
//		modelDSNV.addRow(new Object[]{"1", "Tran Van Nhan", "0987654321", "tranvannhan@gmail.com", "Th??? ?????c, H??? Ch?? Minh"});
		
		renderData();
		setDisable();
		
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		tblNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int idx = tblNhanVien.getSelectedRow();
				if(idx == -1 && txtTenNv.getText().equals("")) {
					setDisable();
				}
				if(idx != -1) {
					setEnable();
					
					NhanVien nv = dsnv.get(idx);
					txtMaNv.setText(String.valueOf(nv.getMaNv()));
					txtTenNv.setText(nv.getTenNv());
					txtSdt.setText(nv.getSoDienThoai());
					txtDiaChi.setText(nv.getDiaChi());
					
					cboCaLam.setSelectedIndex(nv.getCaLamViec() - 1);
					cboChucVu.setSelectedIndex(nv.getChucNang() - 1);
				}
			}
		});
		
		btnSuaNv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tblNhanVien.clearSelection();
				
				int maNV = Integer.parseInt(txtMaNv.getText());
				String tenNV = txtTenNv.getText();
				String sdt = txtSdt.getText();
				String diaChi = txtDiaChi.getText();
				int caLam = cboCaLam.getSelectedIndex() + 1;
				int chucVu = cboChucVu.getSelectedIndex() + 1;
				
				if(tenNV.equals("")|| kiemTraSo(tenNV)) {
					JOptionPane.showMessageDialog(contentPane, "T??n kh??ng h???p l???");
					return;
				}
				
				boolean ktSdt  = sdt.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$");	
				if(ktSdt == false) {
					JOptionPane.showMessageDialog(contentPane, "S??? ??i???n tho???i kh??ng h???p l???");
					return;
				}
				if(!String.valueOf(caLam).matches("[1-2]")) {
					JOptionPane.showMessageDialog(contentPane, "Ca l??m ch??? c?? 1 ho???c 2, 1-s??ng 2-chi???u");
					return;
				}

				if(!String.valueOf(chucVu).matches("[1-3]")) {
					JOptionPane.showMessageDialog(contentPane,"ch???c v??? kh??ng h???p l???");
					return;
				}
					
				
				NhanVien nv = new NhanVien(maNV, tenNV, sdt, diaChi,caLam,chucVu);
				boolean kq;
				try {
					kq = new NhanVienDAO().suaNV(nv);
					if(kq) {
						JOptionPane.showMessageDialog(contentPane, "S???a th??nh c??ng");
						renderData();
					}else {
						JOptionPane.showMessageDialog(contentPane, "C?? l???i x???y ra");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lamMoi();
				setDisable();
			}
		});
		
		btnXoaNv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tblNhanVien.getSelectedRow();
				if(index != -1) {
					
					int choose = JOptionPane.showConfirmDialog(contentPane, "Ch???c ch???n x??a!","X??c nh???n", JOptionPane.YES_NO_OPTION);
					if(choose == 0) {
						tblNhanVien.clearSelection();
						try {
							boolean kq = new NhanVienDAO().xoaNV(dsnv.get(index));
							System.out.println(kq);
							if(kq) {
								JOptionPane.showMessageDialog(contentPane, "X??a th??nh c??ng");
								renderData();
								lamMoi();
								setDisable();
								return;
							}else {
								JOptionPane.showMessageDialog(contentPane, "Kh??ng th??? x??a nh??n vi??n n??y");
								return;
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaNv.setText("");
				txtTenNv.setText("");
				txtSdt.setText("");
				txtDiaChi.setText("");
				cboCaLam.setSelectedIndex(0);
				cboChucVu.setSelectedIndex(0);
				try {
					renderData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtNhapLieu.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,"c???n nh???p d??? li???u t??m ki???m");
					return;
				}
				String key = "";
					
				if(cboLoaiTimKiem.getSelectedItem().equals("T??n NV")) 
					key = "TenNv";							
				else if(cboLoaiTimKiem.getSelectedItem().equals("S??? ??i???n tho???i")) 
					key = "SoDienThoai";		
				else if(cboLoaiTimKiem.getSelectedItem().equals("?????a ch???")) 
					key = "DiaChi";
				System.out.println(key);
				String sql = " " + key + " like " + "N'%" +txtNhapLieu.getText()+ "%'" ;
				System.out.println(sql);
				dsnv = new ArrayList<NhanVien>();
				try {
					dsnv = new NhanVienDAO().TimKiem(sql);
					renderDataTimKiem(dsnv);
					if(dsnv.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Kh??ng t??m th???y nh??n vi??n n??o");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});
	}

	public void renderData() throws SQLException {
		//modelDSNV.getDataVector().removeAllElements();
		modelDSNV.setRowCount(0);
		dsnv = new NhanVienDAO().getDSNV();
		dsnv.forEach(nv -> {
			String caLamViec = "S??ng";
			if(nv.getCaLamViec() == 2)
				caLamViec = "Chi???u";
			
			String chucNang = "Ng?????i qu???n l??";
			if(nv.getChucNang() == 1)
				chucNang = "Nh??n vi??n b??n h??ng";
			else if(nv.getChucNang() == 2)
				chucNang = "Nh??n vi??n qu???n l?? s???n ph???m";
			
			
			modelDSNV.addRow(new Object[] {
					nv.getMaNv(), 
					nv.getTenNv(), 
					nv.getSoDienThoai(), 
					nv.getDiaChi(),
					caLamViec,
					chucNang,
					nv.getTenTk()
			});
		});
		
		tblNhanVien.revalidate();
		tblNhanVien.repaint();
		
	}
	public void lamMoi() {
		txtMaNv.setText("");
		txtTenNv.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
		cboCaLam.setSelectedIndex(0);
		cboChucVu.setSelectedIndex(0);
	}
	public void setDisable() {
		btnSuaNv.setEnabled(false);
		btnXoaNv.setEnabled(false);
	}
	public void setEnable() {
		btnSuaNv.setEnabled(true);
		btnXoaNv.setEnabled(true);
	}
	public void renderDataTimKiem(List<NhanVien> dsnv) throws SQLException {
		tblNhanVien.clearSelection();

		modelDSNV.getDataVector().removeAllElements();

		dsnv.forEach(nv -> {
			String caLamViec = "S??ng";
			if(nv.getCaLamViec() == 2)
				caLamViec = "Chi???u";
			
			String chucNang = "Ng?????i qu???n l??";
			if(nv.getChucNang() == 1)
				chucNang = "Nh??n vi??n b??n h??ng";
			else if(nv.getChucNang() == 2)
				chucNang = "Nh??n vi??n qu???n l?? s???n ph???m";
			
			modelDSNV.addRow(new Object[] {
					nv.getMaNv(), 
					nv.getTenNv(), 
					nv.getSoDienThoai(), 
					nv.getDiaChi(),
					caLamViec,
					chucNang,
					nv.getTenTk()
			});
		});

		tblNhanVien.revalidate();
		tblNhanVien.repaint();
	}
	public boolean kiemTraSo(String ten) {
		char arrTen[] = ten.toCharArray();
		for(int i=0;i<ten.length();i++) {
			String cTen = String.valueOf(arrTen[i]);
			if(cTen.matches("[0-9]"))
				return true;
		}
		return false;
	}
	
	public JPanel getContentPane() {
		 return this.out;
	 }
}
