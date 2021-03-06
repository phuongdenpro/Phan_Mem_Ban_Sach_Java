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

import dao.LoaiSanPhamDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;
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

public class SanPhamKhac_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable table;

	private JTextField txtSoLuong;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBan;

	private JComboBox<String> cboListMaloai;
	private JTextField txtMaSanPham;
	private JTextField txtNCC;
	private ArrayList<SanPham> dsssp;
	private DefaultTableModel modelDSSanPham;
	private ArrayList<LoaiSanPham> dsLoai;
	private List<SanPham> dssptim;
	private JTextField txtTenSanPham;
	private SanPhamDAO sanphamDAO;

	private LoaiSanPhamDAO loaiDAO;
	private NhaCungCapDAO nCCDAO;
	private ArrayList<NhaCungCap> dsNCC;
	private boolean isTimKiem = false;
	private JComboBox<String> cboListNCC;
	private DefaultComboBoxModel<String> modelMaLoai;
	private DefaultComboBoxModel<String> modelNCC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanPhamKhac_GUI frame = new SanPhamKhac_GUI();
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
	public SanPhamKhac_GUI() throws SQLException {
		setTitle("Qu???n L?? D???ng C??? H???c T???p");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		sanphamDAO = new SanPhamDAO();
		loaiDAO = new LoaiSanPhamDAO();
		nCCDAO = new NhaCungCapDAO();
		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("QU???N L?? D???NG C??? H???C T???P");
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

		JLabel lblTieuDe = new JLabel("Th??ng tin s???n ph???m");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTin.add(verticalStrut_1);

		JPanel pnMaSanPham = new JPanel();
		FlowLayout fl_pnMaSP = (FlowLayout) pnMaSanPham.getLayout();
		fl_pnMaSP.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaSanPham);

		JLabel lblMaSanPham = new JLabel("M?? S???n Ph???m:  ");
		lblMaSanPham.setPreferredSize(new Dimension(100, 14));
		pnMaSanPham.add(lblMaSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEnabled(false);
		txtMaSanPham.setPreferredSize(new Dimension(7, 30));
		pnMaSanPham.add(txtMaSanPham);
		txtMaSanPham.setColumns(20);

		JPanel pnTenSanPham = new JPanel();
		FlowLayout fl_pnTenSP = (FlowLayout) pnTenSanPham.getLayout();
		fl_pnTenSP.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenSanPham);

		JLabel lblTenSanPham = new JLabel("T??n S???n Ph???m:");
		lblTenSanPham.setPreferredSize(new Dimension(100, 14));
		pnTenSanPham.add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setPreferredSize(new Dimension(7, 30));
		txtTenSanPham.setColumns(20);
		// PromptSupport.setPrompt("t??n kh??ch h??ng", txtTenKh);
//		new Placeholder().placeholder(txtTenKh, "t??n kh??ch h??ng");
		pnTenSanPham.add(txtTenSanPham);

		JPanel pnnhaCC = new JPanel();
		FlowLayout fl_pnNCC = (FlowLayout) pnnhaCC.getLayout();
		fl_pnNCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnnhaCC);

		JLabel lblNCC = new JLabel("Nh?? cung c???p:");
		lblNCC.setPreferredSize(new Dimension(100, 14));
		pnnhaCC.add(lblNCC);
		
		modelNCC = new DefaultComboBoxModel<String>();
		cboListNCC = new JComboBox<String>(modelNCC);
		cboListNCC.setPreferredSize(new Dimension(202, 30));
		cboListNCC.addItem("");

		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);

		pnnhaCC.add(cboListNCC);

		JPanel pnSoLuong = new JPanel();
		FlowLayout fl_pnSoLuong = (FlowLayout) pnSoLuong.getLayout();
		fl_pnSoLuong.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoLuong);

		JLabel lblSoLuong = new JLabel("S??? l?????ng:");
		lblSoLuong.setPreferredSize(new Dimension(100, 14));
		pnSoLuong.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(new Dimension(7, 30));
		txtSoLuong.setColumns(20);
		// PromptSupport.setPrompt("Example@gmail.com", txtEmail);

		pnSoLuong.add(txtSoLuong);

		JPanel pnGiaNhap = new JPanel();
		FlowLayout fl_pnGiaNhap = (FlowLayout) pnGiaNhap.getLayout();
		fl_pnGiaNhap.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnGiaNhap);

		JLabel lblGiaNhap = new JLabel("Gi?? nh???p:");
		lblGiaNhap.setPreferredSize(new Dimension(100, 14));
		pnGiaNhap.add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setPreferredSize(new Dimension(7, 30));
		txtGiaNhap.setColumns(20);
		// PromptSupport.setPrompt("09xx xxx xxx ", txtSdt);
//		new Placeholder().placeholder(txtSdt, "09xx xxx xxx");
		pnGiaNhap.add(txtGiaNhap);

		JPanel pnGiaBan = new JPanel();
		FlowLayout fl_pnGiaBan = (FlowLayout) pnGiaBan.getLayout();
		fl_pnGiaBan.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnGiaBan);

		JLabel lblGiaBan = new JLabel("Gi?? b??n:");
		lblGiaBan.setPreferredSize(new Dimension(100, 14));
		pnGiaBan.add(lblGiaBan);

		txtGiaBan = new JTextField();
		txtGiaBan.setPreferredSize(new Dimension(7, 30));
		txtGiaBan.setColumns(20);
		// PromptSupport.setPrompt("S??? nh??, t??n ???????ng, t???nh th??nh", txtDiaChi);

		pnGiaBan.add(txtGiaBan);

		JPanel pnLoai = new JPanel();
		FlowLayout fl_pnLoai = (FlowLayout) pnLoai.getLayout();
		fl_pnLoai.setAlignment(FlowLayout.LEFT);
		JLabel lblMaLoai = new JLabel("Lo???i s???n ph???m:");
		lblMaLoai.setPreferredSize(new Dimension(100, 14));
		pnLoai.add(lblMaLoai);
		pnThongTin.add(pnLoai);
		
		modelMaLoai = new DefaultComboBoxModel<String>();
		cboListMaloai = new JComboBox<String>(modelMaLoai);

		cboListMaloai.setPreferredSize(new Dimension(202, 30));
//		cboListMaloai.setModel(new javax.swing.DefaultComboBoxModel<>());
		cboListMaloai.addItem("");
		// cboListMaloai.setSize(7, 30);
		// PromptSupport.setPrompt("S??? nh??, t??n ???????ng, t???nh th??nh", txtDiaChi);

		pnLoai.add(cboListMaloai);

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

		cboLoaiTimKiem.addElement((String) "M?? S???n Ph???m");
		cboLoaiTimKiem.addElement((String) "T??n S???n Ph???m");
		cboLoaiTimKiem.addElement((String) "Lo???i S???n Ph???m");
		cboLoaiTimKiem.addElement((String) "Nh?? Cung C???p");

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

		String[] cols_dssanpham = { "M?? s???n ph???m", "T??n s???n ph???m", "Nh?? cung c???p", "S??? l?????ng", "Gi?? nh???p", "Gi?? b??n",
				"Lo???i s???n ph???m" };
		modelDSSanPham = new DefaultTableModel(cols_dssanpham, 0);
		table = new JTable(modelDSSanPham);
		JScrollPane scrTableSanPham = new JScrollPane(table);
		scrTableSanPham.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableSanPham.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableSanPham);

		// modelDSKH.addRow(new Object[]{"1", "Tran Van Nhan", "0987654321",
		// "tranvannhan@gmail.com", "Th??? ?????c, H??? Ch?? Minh"});
		try {
			renderData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.addMouseListener(this);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSanPham.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
						|| txtSoLuong.getText().equals("") || txtGiaNhap.getText().equals("")
						|| txtGiaBan.getText().equals("") || cboListMaloai.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(out, "Thi????u d???? li????u ??????u va??o");
				} else if (ktdulieu()) {
					int masp = sanphamDAO.getSanPhamCuoiCung().getMaSp() + 1;
					String tensp = txtTenSanPham.getText().trim();
					String nc = cboListNCC.getSelectedItem().toString();
					NhaCungCap ncc = nCCDAO.getNCCByTenNCC(nc);

					String soluong = txtSoLuong.getText().trim();
					String giaNhap = txtGiaNhap.getText().trim();
					String giasp = txtGiaBan.getText().trim();
					String loaispk = cboListMaloai.getSelectedItem().toString();
					LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaispk);

					SanPham sp = new SanPham(masp, tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
							Double.parseDouble(giasp), loaisp, ncc);
					if (timma(sp.getMaSp())) {
						JOptionPane.showMessageDialog(out, "Ma?? ??a?? t????n ta??i");
					} else {
						boolean result = sanphamDAO.create(sp);
						if (result) {
							modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(),
									sp.getNhaCungCap().getTenNCC(), sp.getSoLuong(),
									Currency.format((int) sp.getGiaNhap()),
									Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
							JOptionPane.showMessageDialog(out, "Th??m th??nh c??ng");

						} else {
							JOptionPane.showMessageDialog(out, "Th??m th???t b???i");
						}
					}
				}
			}

		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (txtTenSanPham.getText().equals("") || cboListNCC.getSelectedItem().toString().equals("")
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
						boolean result = sanphamDAO.capNhat(sp);
						if (result == true) {

							modelDSSanPham.setValueAt(sp.getMaSp(), row, 0);
							modelDSSanPham.setValueAt(sp.getTenSp(), row, 1);
							modelDSSanPham.setValueAt(sp.getNhaCungCap().getTenNCC(), row, 2);
							modelDSSanPham.setValueAt(sp.getSoLuong(), row, 3);
							modelDSSanPham.setValueAt(Currency.format((int) sp.getGiaNhap()), row, 4);
							modelDSSanPham.setValueAt(Currency.format((int) sp.getGiaSp()), row, 5);
							modelDSSanPham.setValueAt(sp.getLoaiSanPham().getTenLoai(), row, 6);
							JOptionPane.showMessageDialog(out, "C???p nh???p s???n ph???m th??nh c??ng");
							modelDSSanPham.fireTableDataChanged();
							sanphamDAO.getListSanPhamKhac();
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
							boolean result = sanphamDAO.delete(sp);
							if (result) {
								modelDSSanPham.removeRow(row);
								JOptionPane.showMessageDialog(out, "X??a th??nh c??ng");
								txtMaSanPham.setText("");
								txtTenSanPham.setText("");
								cboListNCC.setSelectedItem("");
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
				txtMaSanPham.setText("");
				txtTenSanPham.setText("");
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
						if (cboLoaiTimKiem.getSelectedItem().toString().equals("M?? S???n Ph???m")) {
							key = "SanPham.MaSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("T??n S???n Ph???m")) {
							key = "SanPham.TenSP";
						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Lo???i S???n Ph???m")) {
							key = "LoaiSanPham.TenLoai";

						} else if (cboLoaiTimKiem.getSelectedItem().toString().equals("Nh?? Cung C???p")) {
							key = "NhaCungCap.TenNCC";
						}
						dssptim = sanphamDAO.timKiemSanPhamKhac(key, txtNhapLieu.getText());

						if (dssptim.size() == 0) {
							JOptionPane.showMessageDialog(out, "Kh??ng t??m th???y d??? li???u theo y??u c???u c???n t??m");
							table.clearSelection();
							modelDSSanPham.getDataVector().removeAllElements();
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

					modelDSSanPham.getDataVector().removeAllElements();
					renderData();
					isTimKiem = false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	private void loadCboMaLoai() throws SQLException {
		modelMaLoai.removeAllElements();
		dsLoai = new LoaiSanPhamDAO().getDanhSachLoaiSanPhamKhac();
		for (LoaiSanPham loai : dsLoai) {
			String ma = loai.getTenLoai();
			modelMaLoai.addElement(String.valueOf(ma));
		}
	}

	private void loadCboNCC() throws SQLException {
		modelNCC.removeAllElements();
		dsNCC = nCCDAO.getListNhaCungCap();
		for (NhaCungCap ncc : dsNCC) {
			String ma = ncc.getTenNCC();
			modelNCC.addElement(String.valueOf(ma));
		}
	}

//
	private SanPham getSelectedDataTable() {
		String masp = txtMaSanPham.getText().trim();
		String tensp = txtTenSanPham.getText().trim();
		String nc = cboListNCC.getSelectedItem().toString();
		NhaCungCap ncc = nCCDAO.getNCCByTenNCC(nc);

		String soluong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giasp = txtGiaBan.getText().trim();
		String loaispk = cboListMaloai.getSelectedItem().toString();
		LoaiSanPham loaisp = loaiDAO.getLoaiByTenLoai(loaispk);

		SanPham sp = new SanPham(Integer.parseInt(masp), tensp, Integer.parseInt(soluong), Double.parseDouble(giaNhap),
				Double.parseDouble(giasp), loaisp, ncc);
		return sp;
	}

	private boolean ktdulieu() {
		String tenSP = txtTenSanPham.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String giaBan = txtGiaBan.getText().trim();
		if (tenSP.equals("")) {
			JOptionPane.showMessageDialog(this, "T??n s???n ph???m kh??ng ???????c ????? tr???ng");
			txtTenSanPham.selectAll();
			txtTenSanPham.requestFocus();
			return false;
		}
		if (tenSP.length()<2) {
			JOptionPane.showMessageDialog(this, "T??n ph???i ??t nh???t l?? 2 k?? t???");
			txtTenSanPham.selectAll();
			txtTenSanPham.requestFocus();
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

	public void renderData() throws SQLException {
		// modelDSSach.getDataVector().removeAllElements();
		table.clearSelection();

		modelDSSanPham.getDataVector().removeAllElements();
		dsssp = new SanPhamDAO().getListSanPhamKhac();

		dsssp.forEach(sp -> {
			modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(), sp.getNhaCungCap().getTenNCC(),
					sp.getSoLuong(), Currency.format((int) sp.getGiaNhap()),
					Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
		});
		
		loadCboMaLoai();
		loadCboNCC();
	}

	public void renderDataTimKiem() throws SQLException {
		table.clearSelection();

		modelDSSanPham.getDataVector().removeAllElements();

		dssptim.forEach(sp -> {
			modelDSSanPham.addRow(new Object[] { sp.getMaSp(), sp.getTenSp(), sp.getNhaCungCap().getTenNCC(),
					sp.getSoLuong(), Currency.format((int) sp.getGiaNhap()),
					Currency.format((int) sp.getGiaSp()), sp.getLoaiSanPham().getTenLoai() });
		});

		table.revalidate();
		table.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaSanPham.setText(modelDSSanPham.getValueAt(row, 0).toString());
		txtTenSanPham.setText(modelDSSanPham.getValueAt(row, 1).toString());
		cboListNCC.setSelectedItem(modelDSSanPham.getValueAt(row, 2).toString());
		txtSoLuong.setText(modelDSSanPham.getValueAt(row, 3).toString());
		txtGiaNhap.setText(modelDSSanPham.getValueAt(row, 4).toString().replaceAll("[^\\d]", ""));
		txtGiaBan.setText(modelDSSanPham.getValueAt(row, 5).toString().replaceAll("[^\\d]", ""));
		cboListMaloai.setSelectedItem(modelDSSanPham.getValueAt(row, 6).toString());
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
}
