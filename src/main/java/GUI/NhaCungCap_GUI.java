package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import connectdb.ConnectDB;
import entity.DonDatHang;
import entity.KhachHang;
import entity.SanPham;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import dao.DonDatHangDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
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
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class NhaCungCap_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;

	private JComboBox<String> cboListMaloai;
	private SanPhamDAO sach_DAO;
	private LoaiSanPhamDAO loaiDAO;

	private ArrayList<NhaCungCap> dsncc;
	private List<SanPham> dssachtim;
	private ArrayList<LoaiSanPham> dsLoai;
	private ArrayList<NhaCungCap> dsNCC;
	private JButton btnThem;

	private NhaCungCapDAO nhaCCDAO;

	private boolean isTimKiem = false;
	// private ArrayList<entity.SanPham> dsSanpham;

	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtSoDt;
	private DefaultTableModel modelNCC;
	private List<NhaCungCap> dssachnccTim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhaCungCap_GUI frame = new NhaCungCap_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public NhaCungCap_GUI() throws SQLException {

//		try {
//			ConnectDB.getInstance().ConnectDB();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		sach_DAO = new SanPhamDAO();
		loaiDAO = new LoaiSanPhamDAO();
		nhaCCDAO = new NhaCungCapDAO();
		setTitle("Qu???n L?? Nh?? Cung C???p");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QU???N L?? NH?? CUNG C???P");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		// title.setHorizontalAlignment(ABORT);
		out.add(top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane, BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		// pnLeft.setBorder();
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);

		JPanel pnThongTin = new JPanel();
		pnLeft.add(pnThongTin);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(35);
		pnThongTin.add(verticalStrut_2);

		JPanel pnTieuDe = new JPanel();
		pnThongTin.add(pnTieuDe);

		JLabel lblTieuDe = new JLabel("Th??ng tin nh?? cung c??p");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut_1);

		JPanel pnMaNCC = new JPanel();
		FlowLayout fl_pnMaNCC = (FlowLayout) pnMaNCC.getLayout();
		fl_pnMaNCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaNCC);

		JLabel lblMaNCC = new JLabel("M?? NCC:             ");
		lblMaNCC.setPreferredSize(new Dimension(100, 14));
		pnMaNCC.add(lblMaNCC);

		txtMaNCC = new JTextField();
		txtMaNCC.setEnabled(false);
		txtMaNCC.setPreferredSize(new Dimension(7, 30));
		pnMaNCC.add(txtMaNCC);
		txtMaNCC.setColumns(20);

		JPanel pnTenNCC = new JPanel();
		FlowLayout fl_pnTenncc = (FlowLayout) pnTenNCC.getLayout();
		fl_pnTenncc.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenNCC);

		JLabel lblTenncc = new JLabel("T??n NCC:");
		lblTenncc.setPreferredSize(new Dimension(100, 14));
		pnTenNCC.add(lblTenncc);

		txtTenNCC = new JTextField();
		txtTenNCC.setPreferredSize(new Dimension(7, 30));
		txtTenNCC.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnTenNCC.add(txtTenNCC);

		JPanel pnDiaChi = new JPanel();
		FlowLayout fl_pnDiaChi = (FlowLayout) pnDiaChi.getLayout();
		fl_pnDiaChi.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnDiaChi);

		JLabel lblDiaChi = new JLabel("?????a ch???:");
		lblDiaChi.setPreferredSize(new Dimension(100, 14));
		pnDiaChi.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(7, 30));
		txtDiaChi.setColumns(20);
		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);

		pnDiaChi.add(txtDiaChi);

		JPanel pnSoDT = new JPanel();
		FlowLayout fl_pnSoDT = (FlowLayout) pnSoDT.getLayout();
		fl_pnSoDT.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoDT);

		JLabel lblSoDT = new JLabel("S??? ??i???n tho???i:");
		lblSoDT.setPreferredSize(new Dimension(100, 14));
		pnSoDT.add(lblSoDT);

		txtSoDt = new JTextField();
		txtSoDt.setPreferredSize(new Dimension(7, 30));
		txtSoDt.setColumns(20);
		// PromptSupport.setPrompt("09xx xxx xxx ", txtSdt);
//		new Placeholder().placeholder(txtSdt, "09xx xxx xxx");
		pnSoDT.add(txtSoDt);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut);

		JPanel pnChucNang = new JPanel();
		pnThongTin.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(2, 0, 5, 5));

		JButton btnThem = new JButton("Th??m");
		btnThem.setBackground(Color.WHITE);
		btnThem.setPreferredSize(new Dimension(70, 35));
		btnThem.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThem.setIconTextGap(10);
		out.getRootPane().setDefaultButton(btnThem);
		pnChucNang.add(btnThem);

		JButton btnSua = new JButton("S???a");
		btnSua.setBackground(Color.WHITE);
		btnSua.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnSua.setIconTextGap(30);
		pnChucNang.add(btnSua);

		JButton btnXoa = new JButton("X??a");
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setIcon(new ImageIcon("data\\images\\trash.png"));
		btnXoa.setIconTextGap(10);
		pnChucNang.add(btnXoa);

		JButton btnLamMoi = new JButton("L??m m???i");
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setIcon(new ImageIcon("data\\images\\refresh.png"));
		btnLamMoi.setIconTextGap(10);
		pnChucNang.add(btnLamMoi);

		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		DefaultComboBoxModel cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		JComboBox cmbLoaiTimKiem = new JComboBox(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("t??m ki???m theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);
		cboLoaiTimKiem.addElement((String) "M?? NCC");
		cboLoaiTimKiem.addElement((String) "T??n NCC");
		cboLoaiTimKiem.addElement((String) "S??? ??i???n tho???i");
		// cboLoaiTimKiem.addElement((String) "Lo???i S??ch");

		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		// PromptSupport.setPrompt("nh???p li???u t??m ki???m", txtNhapLieu);
//		new Placeholder().placeholder(txtNhapLieu, "nh???p li???u t??m ki???m");
		txtNhapLieu.setColumns(30);

		JButton btnTimKiem = new JButton("T??m ki???m");
		btnTimKiem.setPreferredSize(new Dimension(130, 25));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setIcon(new ImageIcon("data\\images\\search_16.png"));
		pnTimKiem.add(btnTimKiem);

		JButton btnLamMoiDuLieu = new JButton("L??m m???i d??? li???u");
		btnLamMoiDuLieu.setPreferredSize(new Dimension(150, 25));
		btnLamMoiDuLieu.setBackground(Color.WHITE);
		btnLamMoiDuLieu.setIcon(new ImageIcon("data\\images\\refresh.png"));
		pnTimKiem.add(btnLamMoiDuLieu);

		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));

		String[] cols_ds = { "M?? nh?? cung c???p", "T??n nh?? cung c???p", "?????a ch???", "S??? ??i???n tho???i" };

		modelNCC = new DefaultTableModel(cols_ds, 0) {
			// kh??a kh??ng cho ng?????i d??ng nh???p tr??n table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table = new JTable(modelNCC);
		JScrollPane scrTableSach = new JScrollPane(table);
		scrTableSach.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableSach.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableSach);

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenNCC.getText().equals("") || txtDiaChi.getText().equals("") || txtSoDt.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Thi????u d???? li????u ??????u va??o");
				} else if (ktdulieu()) {
					String tenncc = txtTenNCC.getText().trim();
					String diachi = txtDiaChi.getText().trim();
					String sdt = txtSoDt.getText().trim();
					NhaCungCap ncc = new NhaCungCap(tenncc, diachi, sdt);

					if (timma(ncc.getMaNCC())) {
						JOptionPane.showMessageDialog(out, "Ma?? ??a?? t????n ta??i");
					} else

						nhaCCDAO.create(ncc);
					NhaCungCap nc = nhaCCDAO.getNCCByTenNCC(ncc.getTenNCC());
					modelNCC.addRow(
							new Object[] { nc.getMaNCC(), nc.getTenNCC(), nc.getDiaChi(), nc.getSoDienThoai() });

				}
			}

		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtMaNCC.getText().equals("") || txtTenNCC.getText().equals("") || txtDiaChi.getText().equals("")
						|| txtSoDt.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "Thi????u d???? li????u ??????u va??o");
				} else if (ktdulieu()) {
					NhaCungCap ncc = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "B???n ch??a ch???n d??ng c???n s???a", "C???nh b??o",
								JOptionPane.WARNING_MESSAGE);
					} else {
						boolean result = nhaCCDAO.capNhat(ncc);
						if (result == true) {

							modelNCC.setValueAt(ncc.getMaNCC(), row, 0);
							modelNCC.setValueAt(ncc.getTenNCC(), row, 1);
							modelNCC.setValueAt(ncc.getDiaChi(), row, 2);
							modelNCC.setValueAt(ncc.getSoDienThoai(), row, 3);
							JOptionPane.showMessageDialog(out, "C???p nh???p th??nh c??ng");
							modelNCC.fireTableDataChanged();
							sach_DAO.getListSach();
						} else {
							JOptionPane.showMessageDialog(out, "L???i: C???p nh???t th???t b???i");
						}
					}

				}
			}

		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					NhaCungCap ncc = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "B???n ch??a ch???n s???n ph???m c???n xo?? !!!");
					} else {
						int select;
						select = JOptionPane.showConfirmDialog(out, "B???n c?? mu???n xo?? nh?? cung c???p ???? ch???n ?", "C???nh b??o",
								JOptionPane.YES_NO_OPTION);
						if (select == JOptionPane.YES_OPTION) {
							boolean result = nhaCCDAO.delete(ncc);
							if (result) {
								modelNCC.removeRow(row);
								JOptionPane.showMessageDialog(out, "X??a th??nh c??ng");
								txtMaNCC.setText("");
								txtTenNCC.setText("");
								txtDiaChi.setText("");
								txtSoDt.setText("");
							} else {
								JOptionPane.showMessageDialog(out, "X??a th???t b???i");
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(out, "L???i! X??a th???t b???i");

				}
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaNCC.setText("");
				txtTenNCC.setText("");
				txtDiaChi.setText("");
				txtSoDt.setText("");
				txtNhapLieu.setText("");

			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtNhapLieu.getText().equals("")) {
					JOptionPane.showMessageDialog(out, "C????n nh????p d??? li???u s???n ph???m c????n ti??m", "C???nh b??o",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						String key = "MaNCC";
						if (cboLoaiTimKiem.getSelectedItem().toString().equalsIgnoreCase("M?? NCC")) {
							key = "MaNCC";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equalsIgnoreCase("T??n NCC")) {
							key = "TenNCC";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equalsIgnoreCase("S??? ??i???n tho???i")) {
							key = "SoDienThoai";

						} 
						dssachnccTim = nhaCCDAO.timKiem(key, txtNhapLieu.getText());
						if (dssachnccTim.size() == 0) {
							JOptionPane.showMessageDialog(out, "Kh??ng t??m th???y d??? li???u theo y??u c???u c???n t??m");
							table.clearSelection();
							modelNCC.getDataVector().removeAllElements();
							table.revalidate();
							table.repaint();
							isTimKiem = false;
						} else {
							renderDataTimKiem();
							isTimKiem = true;
						}	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLamMoiDuLieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					table.clearSelection();

					modelNCC.getDataVector().removeAllElements();
					renderData();
					isTimKiem = false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.addMouseListener(this);

		try {
			renderData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNCC.setText(modelNCC.getValueAt(row, 0).toString());
		txtTenNCC.setText(modelNCC.getValueAt(row, 1).toString());
		txtDiaChi.setText(modelNCC.getValueAt(row, 2).toString());
		txtSoDt.setText(modelNCC.getValueAt(row, 3).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	


	private NhaCungCap getSelectedDataTable() {
		String mancc = txtMaNCC.getText().trim();
		String tenncc = txtTenNCC.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String sdt = txtSoDt.getText().trim();
		NhaCungCap ncc = new NhaCungCap(Integer.parseInt(mancc), tenncc, diachi, sdt);

		return ncc;
	}

//
	private boolean ktdulieu() {
		//String MaKH = txtMaNCC.getText().trim();
		String TenNCC = txtTenNCC.getText().trim();
		String DiaChiKH = txtDiaChi.getText().trim();
		String Sdt = txtSoDt.getText().trim();
		if(TenNCC.equals("")){
			JOptionPane.showMessageDialog(this, "T??n kh??ng ????????c b??? tr???ng");
			txtTenNCC.selectAll();
			txtTenNCC.requestFocus();
            return false;
        }
		
		if(!TenNCC.matches("^[^0-9]{2,25}$")){
			JOptionPane.showMessageDialog(this,"T??n kh??ng ???????c ch???a s???, ??t nh???t l?? 2 k?? t???");
            return false;
        }
		
		
		if(!Sdt.matches("^0[0-9]{9}$")){
			JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???");
            return false;
        }
		return true;

	}

//
	public boolean timma(int ma) {
		int temp;
		for (int i = 0; i < table.getRowCount(); i++) {
			temp = (int) table.getValueAt(i, 0);
			if (temp == ma) {
				table.setRowSelectionInterval(i, i);
				// scroll ?????n d??ng ???????c ch???n
				Rectangle cellRect = table.getCellRect(i, 0, true);
				table.scrollRectToVisible(cellRect);
				return true;
			}
		}
		return false;
	}
//
	
	public void renderData() throws SQLException {
		table.clearSelection();

		modelNCC.getDataVector().removeAllElements();
		dsncc = nhaCCDAO.getListNhaCungCap();

		dsncc.forEach(ncc -> {
			modelNCC.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai() });
		});
	}
	public void renderDataTimKiem() throws SQLException {
		table.clearSelection();

		modelNCC.getDataVector().removeAllElements();

		dssachnccTim.forEach(ncc -> {
			modelNCC.addRow(new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai() });
		});

		table.revalidate();
		table.repaint();
	}
}
