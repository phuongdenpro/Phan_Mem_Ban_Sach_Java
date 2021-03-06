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
import util.Currency;
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

public class Sach_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;

	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtNXB;
	private JTextField txtSoLuong;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBan;
	private JTextField txtMaLoai;
	private JComboBox<String> cboListMaloai;
	private SanPhamDAO sach_DAO;
	private LoaiSanPhamDAO loaiDAO;
	private NhaCungCapDAO nhaCCDAO;

	private ArrayList<SanPham> dssach;
	private List<SanPham> dssachtim;
	private ArrayList<NhaCungCap> dsNCC1;
	private ArrayList<LoaiSanPham> dsLoai;
	private ArrayList<NhaCungCap> dsNCC;
	private JButton btnThem;

	private boolean isTimKiem = false;
	// private ArrayList<entity.SanPham> dsSanpham;
	private DefaultTableModel modelDSSach;
	private JComboBox<String> cboListNCC;
	private JTextField txtTacGia;
	private JTextField txtSoTrang;
	private JTextField txtNamXb;
	private DefaultComboBoxModel<String> modelMaLoai;
	private DefaultComboBoxModel<String> modelNCC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sach_GUI frame = new Sach_GUI();
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
	public Sach_GUI() throws SQLException {

//		try {
//			ConnectDB.getInstance().ConnectDB();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		sach_DAO = new SanPhamDAO();
		loaiDAO = new LoaiSanPhamDAO();
		nhaCCDAO = new NhaCungCapDAO();
		setTitle("Qu???n L?? S??ch");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QU???N L?? S??CH");
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

		JLabel lblTieuDe = new JLabel("Th??ng tin s??ch");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut_1);

		JPanel pnMaSach = new JPanel();
		FlowLayout fl_pnMaKh = (FlowLayout) pnMaSach.getLayout();
		fl_pnMaKh.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaSach);

		JLabel lblMaSach = new JLabel("M?? S??ch:             ");
		lblMaSach.setPreferredSize(new Dimension(100, 14));
		pnMaSach.add(lblMaSach);

		txtMaSach = new JTextField();
		txtMaSach.setEnabled(false);
		txtMaSach.setPreferredSize(new Dimension(7, 25));
		pnMaSach.add(txtMaSach);
		txtMaSach.setColumns(20);

		JPanel pnTenSach = new JPanel();
		FlowLayout fl_pnTenKh = (FlowLayout) pnTenSach.getLayout();
		fl_pnTenKh.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenSach);

		JLabel lblTenSach = new JLabel("T??n S??ch:");
		lblTenSach.setPreferredSize(new Dimension(100, 14));
		pnTenSach.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setPreferredSize(new Dimension(7, 25));
		txtTenSach.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnTenSach.add(txtTenSach);

		JPanel pnTacGia = new JPanel();
		FlowLayout fl_pnTacGia = (FlowLayout) pnTacGia.getLayout();
		fl_pnTacGia.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTacGia);

		JLabel lblTacGia = new JLabel("T??c Gi???:");
		lblTacGia.setPreferredSize(new Dimension(100, 14));
		pnTacGia.add(lblTacGia);

		txtTacGia = new JTextField();
		txtTacGia.setPreferredSize(new Dimension(7, 25));
		txtTacGia.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnTacGia.add(txtTacGia);

		JPanel pnSoTrang = new JPanel();
		FlowLayout fl_pnSoTrang = (FlowLayout) pnSoTrang.getLayout();
		fl_pnSoTrang.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoTrang);

		JLabel lblSoTrang = new JLabel("S??? trang:");
		lblSoTrang.setPreferredSize(new Dimension(100, 14));
		pnSoTrang.add(lblSoTrang);

		txtSoTrang = new JTextField();
		txtSoTrang.setPreferredSize(new Dimension(7, 25));
		txtSoTrang.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnSoTrang.add(txtSoTrang);

		JPanel pnNXB = new JPanel();
		FlowLayout fl_pnNXB = (FlowLayout) pnNXB.getLayout();
		fl_pnNXB.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNXB);

		JLabel lblNXB = new JLabel("Nh?? xu???t b???n:");
		lblNXB.setPreferredSize(new Dimension(100, 14));
		pnNXB.add(lblNXB);

		modelNCC = new DefaultComboBoxModel<String>();
		cboListNCC = new JComboBox<String>(modelNCC);

		cboListNCC.setPreferredSize(new Dimension(202, 25));
		cboListNCC.addItem("");

		pnNXB.add(cboListNCC);

		JPanel pnNamXB = new JPanel();
		FlowLayout fl_pnNamXB = (FlowLayout) pnNamXB.getLayout();
		fl_pnNamXB.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNamXB);

		JLabel lblNamXB = new JLabel("N??m xu???t b???n:");
		lblNamXB.setPreferredSize(new Dimension(100, 14));
		pnNamXB.add(lblNamXB);

		txtNamXb = new JTextField();
		txtNamXb.setPreferredSize(new Dimension(7, 25));
		txtNamXb.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnNamXB.add(txtNamXb);

		JPanel pnSoLuong = new JPanel();
		FlowLayout fl_pnSoLuong = (FlowLayout) pnSoLuong.getLayout();
		fl_pnSoLuong.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoLuong);

		JLabel lblSoLuong = new JLabel("S??? l?????ng:");
		lblSoLuong.setPreferredSize(new Dimension(100, 14));
		pnSoLuong.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(new Dimension(7, 25));
		txtSoLuong.setColumns(20);
		// PromptSupport.setPrompt("09xx xxx xxx ", txtSdt);
//		new Placeholder().placeholder(txtSdt, "09xx xxx xxx");
		pnSoLuong.add(txtSoLuong);

		JPanel pnGiaNhap = new JPanel();
		FlowLayout fl_pnGiaNhap = (FlowLayout) pnGiaNhap.getLayout();
		fl_pnGiaNhap.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnGiaNhap);

		JLabel lblGiaNhap = new JLabel("Gi?? nh???p:");
		lblGiaNhap.setPreferredSize(new Dimension(100, 14));
		pnGiaNhap.add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setPreferredSize(new Dimension(7, 25));
		txtGiaNhap.setColumns(20);
		// PromptSupport.setPrompt("S??? nh??, t??n ???????ng, t???nh th??nh", txtDiaChi);

		pnGiaNhap.add(txtGiaNhap);

		JPanel pnGiaBan = new JPanel();
		FlowLayout fl_pnGiaBan = (FlowLayout) pnGiaBan.getLayout();
		fl_pnGiaBan.setAlignment(FlowLayout.LEFT);
		JLabel lblGiaBan = new JLabel("Gi?? b??n:");
		lblGiaBan.setPreferredSize(new Dimension(100, 14));
		pnGiaBan.add(lblGiaBan);
		pnThongTin.add(pnGiaBan);
		txtGiaBan = new JTextField();
		txtGiaBan.setPreferredSize(new Dimension(7, 25));
		txtGiaBan.setColumns(20);
		// txtGiaBan.setText(new Currency(a));
		// PromptSupport.setPrompt("S??? nh??, t??n ???????ng, t???nh th??nh", txtDiaChi);

		pnGiaBan.add(txtGiaBan);

		JPanel pnMaLoai = new JPanel();
		FlowLayout fl_pnMaLoai = (FlowLayout) pnMaLoai.getLayout();
		fl_pnMaLoai.setAlignment(FlowLayout.LEFT);
		JLabel lblMaLoai = new JLabel("Lo???i s??ch:");
		lblMaLoai.setPreferredSize(new Dimension(100, 14));
		pnMaLoai.add(lblMaLoai);
		pnThongTin.add(pnMaLoai);
		
		modelMaLoai = new DefaultComboBoxModel<String>();
		cboListMaloai = new JComboBox<String>(modelMaLoai);

		cboListMaloai.setPreferredSize(new Dimension(202, 25));
		cboListMaloai.addItem("");
//		cboListMaloai.setModel(new javax.swing.DefaultComboBoxModel<>());
		// cboListMaloai.setSize(7, 30);
		// PromptSupport.setPrompt("S??? nh??, t??n ???????ng, t???nh th??nh", txtDiaChi);

		pnMaLoai.add(cboListMaloai);

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
		cboLoaiTimKiem.addElement((String) "M?? S??ch");
		cboLoaiTimKiem.addElement((String) "T??n S??ch");
		cboLoaiTimKiem.addElement((String) "Nh?? Xu???t B???n");
		cboLoaiTimKiem.addElement((String) "Lo???i S??ch");
		cboLoaiTimKiem.addElement((String) "T??c Gi???");
		cboLoaiTimKiem.addElement((String) "N??m Xu???t B???n");

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

		String[] cols_dssach = { "M?? s??ch", "T??n s??ch", "T??c gi???", "S??? trang", "Nh?? xu???t b???n", "N??m xu???t b???n",
				"S??? l?????ng", "Gi?? nh???p", "Gi?? b??n", "Lo???i S??ch" };

		modelDSSach = new DefaultTableModel(cols_dssach, 0) {
			// kh??a kh??ng cho ng?????i d??ng nh???p tr??n table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table = new JTable(modelDSSach);
		JScrollPane scrTableSach = new JScrollPane(table);
		scrTableSach.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableSach.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableSach);

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSach.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
						|| txtSoLuong.getText().equals("") || txtGiaNhap.getText().equals("")
						|| txtGiaBan.getText().equals("") || cboListMaloai.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(out, "Thi????u d???? li????u ??????u va??o");
				} else if (ktdulieu()) {
					int masp = sach_DAO.getSanPhamCuoiCung().getMaSp() + 1;
					String tensp = txtTenSach.getText().trim();
					String tacGia = txtTacGia.getText().trim();
					String soTrang = txtSoTrang.getText().trim();
					
					String nxb = cboListNCC.getSelectedItem().toString();
					String namXuatBan = txtNamXb.getText().trim();
					NhaCungCap ncc = nhaCCDAO.getNCCByTenNCC(nxb);

					String soluong = txtSoLuong.getText().trim();
					String giaNhap = txtGiaNhap.getText().trim();
					String giasp = txtGiaBan.getText().trim();
					String loaiSach = cboListMaloai.getSelectedItem().toString();
					LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaiSach);
					SanPham sach = new SanPham(masp, tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
							Double.parseDouble(giasp), loaisp, ncc,tacGia,Integer.parseInt(soTrang),Integer.parseInt(namXuatBan));
					if (timma(sach.getMaSp())) {
						JOptionPane.showMessageDialog(out, "Ma?? ??a?? t????n ta??i");
					} else {
						boolean result = sach_DAO.createSach(sach);
						if (result) {
							modelDSSach.addRow(new Object[] { sach.getMaSp(), sach.getTenSp(), sach.getTacGia(), sach.getSoTrang(),
									sach.getNhaCungCap().getTenNCC(), sach.getNamXuatBan(), sach.getSoLuong(),
									Currency.format((int) sach.getGiaNhap()), Currency.format((int) sach.getGiaSp()),
									sach.getLoaiSanPham().getTenLoai() });

						} else {
							JOptionPane.showMessageDialog(out, "Th??m s???n ph???m th???t b???i");
						}
					}
				}
			}

		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSach.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
						|| txtSoLuong.getText().equals("") || txtGiaNhap.getText().equals("")
						|| txtGiaBan.getText().equals("") || cboListMaloai.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(out, "Thi????u d???? li????u ??????u va??o");
				} else if (ktdulieu()) {
					SanPham sp = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "B???n ch??a ch???n d??ng s???n ph???m c???n s???a", "C???nh b??o",
								JOptionPane.WARNING_MESSAGE);
					} else {
						boolean result = sach_DAO.capNhatSach(sp);
						if (result == true) {

							modelDSSach.setValueAt(sp.getMaSp(), row, 0);
							modelDSSach.setValueAt(sp.getTenSp(), row, 1);
							modelDSSach.setValueAt(sp.getTacGia(), row, 2);
							modelDSSach.setValueAt(sp.getSoTrang(), row, 3);
							modelDSSach.setValueAt(sp.getNhaCungCap().getTenNCC(), row, 4);
							modelDSSach.setValueAt(sp.getNamXuatBan(), row, 5);
							modelDSSach.setValueAt(sp.getSoLuong(), row, 6);
							modelDSSach.setValueAt(Currency.format((int) sp.getGiaNhap()), row, 7);
							modelDSSach.setValueAt(Currency.format((int) sp.getGiaSp()), row, 8);
							modelDSSach.setValueAt(sp.getLoaiSanPham().getTenLoai(), row, 9);
							JOptionPane.showMessageDialog(out, "C???p nh???p s???n ph???m th??nh c??ng");
							modelDSSach.fireTableDataChanged();
							sach_DAO.getListSach();
						} else {
							JOptionPane.showMessageDialog(out, "L???i: C???p nh???t s???n ph???m th???t b???i");
						}
					}

				}
			}

		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				SanPham sp = getSelectedDataTable();
//				int row = table.getSelectedRow();
				try {
					SanPham sp = getSelectedDataTable();
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(out, "B???n ch??a ch???n s???n ph???m c???n xo?? !!!");
					} else {
						int select;
						select = JOptionPane.showConfirmDialog(out, "B???n c?? mu???n xo?? s???n ph???m ???? ch???n ?", "C???nh b??o",
								JOptionPane.YES_NO_OPTION);
						if (select == JOptionPane.YES_OPTION) {
							boolean result = sach_DAO.delete(sp);
							if (result) {
								modelDSSach.removeRow(row);
								JOptionPane.showMessageDialog(out, "X??a th??nh c??ng");
								txtMaSach.setText("");
								txtTenSach.setText("");
								txtTacGia.setText("");
								txtSoTrang.setText("");
								cboListNCC.setSelectedItem("");
								txtNamXb.setText("");
								txtSoLuong.setText("");
								txtGiaNhap.setText("");
								txtGiaBan.setText("");
								cboListMaloai.setSelectedItem("");
							} else {
								JOptionPane.showMessageDialog(out, "X??a th???t b???i");
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(out, "L???i! X??a s???n ph???m th???t b???i");

				}
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaSach.setText("");
				txtTenSach.setText("");
				txtTacGia.setText("");
				txtSoTrang.setText("");
				txtNamXb.setText("");
				cboListNCC.setSelectedItem("");
				txtSoLuong.setText("");
				txtGiaNhap.setText("");
				txtGiaBan.setText("");
				cboListMaloai.setSelectedItem("");
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
						String key = "maSP";
						if (cboLoaiTimKiem.getSelectedItem().toString().equals("M?? S??ch")) {
							key = "SanPham.MaSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("T??n S??ch")) {
							key = "SanPham.TenSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Nh?? Xu???t B???n")) {
							key = "NhaCungCap.TenNCC";

						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Lo???i S??ch")) {
							key = "LoaiSanPham.TenLoai";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("T??c Gi???")) {
							key = "SanPham.TacGia";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("N??m Xu???t B???n")) {
							key = "SanPham.namXuatBan";
						}
						dssachtim = sach_DAO.timKiemSach(key, txtNhapLieu.getText());
						// dsloaitim = loaiDAO.timKiem(key, txtNhapLieu.getText());
						if (dssachtim.size() == 0) {
							JOptionPane.showMessageDialog(out, "Kh??ng t??m th???y d??? li???u theo y??u c???u c???n t??m");
							table.clearSelection();
							modelDSSach.getDataVector().removeAllElements();
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

					modelDSSach.getDataVector().removeAllElements();
					renderData();
					isTimKiem = false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.addMouseListener(this);
		// DocDuLieuVaoModel(sach_DAO.getListSach());
		try {
			renderData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			loadCboMaLoai();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loadCboNCC();

	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaSach.setText(modelDSSach.getValueAt(row, 0).toString());
		txtTenSach.setText(modelDSSach.getValueAt(row, 1).toString());
		txtTacGia.setText(modelDSSach.getValueAt(row, 2).toString());
		txtSoTrang.setText(modelDSSach.getValueAt(row, 3).toString());
		cboListNCC.setSelectedItem(modelDSSach.getValueAt(row, 4).toString());
		txtNamXb.setText(modelDSSach.getValueAt(row, 5).toString());
		txtSoLuong.setText(modelDSSach.getValueAt(row, 6).toString());
		txtGiaNhap.setText(modelDSSach.getValueAt(row, 7).toString().replaceAll("[^\\d]", ""));
		txtGiaBan.setText(modelDSSach.getValueAt(row, 8).toString().replaceAll("[^\\d]", ""));
		cboListMaloai.setSelectedItem(modelDSSach.getValueAt(row, 9).toString());

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

	public void renderData() throws SQLException {
		table.clearSelection();

		modelDSSach.getDataVector().removeAllElements();

		dssach = sach_DAO.getListSach();

		dssach.forEach(sach -> {
			modelDSSach.addRow(new Object[] { sach.getMaSp(), sach.getTenSp(), sach.getTacGia(), sach.getSoTrang(),
					sach.getNhaCungCap().getTenNCC(), sach.getNamXuatBan(), sach.getSoLuong(),
					Currency.format((int) sach.getGiaNhap()), Currency.format((int) sach.getGiaSp()),
					sach.getLoaiSanPham().getTenLoai() });
		});
		
		loadCboMaLoai();
		loadCboNCC();
	}

	private void loadCboMaLoai() throws SQLException {
		modelMaLoai.removeAllElements();
		dsLoai = loaiDAO.getDanhSachLoaiSach();
		for (LoaiSanPham loai : dsLoai) {
			String ma = loai.getTenLoai();
			modelMaLoai.addElement(String.valueOf(ma));
		}
	}

	private void loadCboNCC() throws SQLException {
		modelNCC.removeAllElements();
		dsNCC = nhaCCDAO.getListNhaCungCap();
		for (NhaCungCap ncc : dsNCC) {
			String ma = ncc.getTenNCC();
			modelNCC.addElement(String.valueOf(ma));
		}
	}

	private SanPham getSelectedDataTable() {
		String masp = txtMaSach.getText().trim();
		String tensp = txtTenSach.getText().trim();
		String tacGia = txtTacGia.getText().trim();
		String soTrang = txtSoTrang.getText().trim();
		
		String nxb = cboListNCC.getSelectedItem().toString();
		String namXuatBan = txtNamXb.getText().trim();
		NhaCungCap ncc = nhaCCDAO.getNCCByTenNCC(nxb);

		String soluong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giasp = txtGiaBan.getText().trim();
		String loaiSach = cboListMaloai.getSelectedItem().toString();
		LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaiSach);
		SanPham sach = new SanPham(Integer.parseInt(masp), tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
				Double.parseDouble(giasp), loaisp, ncc,tacGia,Integer.parseInt(soTrang),Integer.parseInt(namXuatBan));

		return sach;

	}

	private boolean ktdulieu() {
		String tenSach = txtTenSach.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giaBan = txtGiaBan.getText().trim();
		String namXuatBan = txtNamXb.getText().trim();
		String tacGia = txtTacGia.getText().trim();
		if (tenSach.equals("")) {
			JOptionPane.showMessageDialog(this, "T??n s??ch kh??ng ???????c ????? tr???ng");
			txtTenSach.selectAll();
			txtTenSach.requestFocus();
			return false;
		}
		if (!tacGia.matches("^[^0-9]{2,25}$")) {
			JOptionPane.showMessageDialog(this, "T??n t??c gi??? kh??ng ph???i l?? s???, ??t nh???t l?? 2 k?? t???");
			txtTacGia.selectAll();
			txtTacGia.requestFocus();
			return false;
		}
		if (!namXuatBan.matches("^[0-9]{4,}$")) {
			JOptionPane.showMessageDialog(this, "N??m xu???t b???n ph???i l?? s??? c?? ??t nh???t 4 ch??? s???");
			txtNamXb.selectAll();
			txtNamXb.requestFocus();
			return false;
		}
		if (!soLuong.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "S??? l?????ng kh??ng h???p l???");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		}
		
		
		if (!giaNhap.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "Gi?? nh???p kh??ng h???p l???");
			txtGiaNhap.selectAll();
			txtGiaNhap.requestFocus();
			return false;
		}
		if (!giaBan.matches("^[0-9]{1,}$")) {
			JOptionPane.showMessageDialog(this, "Gi?? b??n kh??ng h???p l???");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		
		double nhap = Double.parseDouble(giaNhap);
		double ban = Double.parseDouble(giaBan);
		
		if(nhap > ban) {
			JOptionPane.showMessageDialog(this, "Gi?? b??n ph???i l???n h??n gi?? nh???p");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		return true;

	}

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

	public void renderDataTimKiem() throws SQLException {
		table.clearSelection();

		modelDSSach.getDataVector().removeAllElements();

		dssachtim.forEach(sach -> {
			modelDSSach.addRow(new Object[] { sach.getMaSp(), sach.getTenSp(), sach.getTacGia(), sach.getSoTrang(),
					sach.getNhaCungCap().getTenNCC(), sach.getNamXuatBan(), sach.getSoLuong(),
					Currency.format((int) sach.getGiaNhap()), Currency.format((int) sach.getGiaSp()),
					sach.getLoaiSanPham().getTenLoai() });
		});

		table.revalidate();
		table.repaint();
	}
}
