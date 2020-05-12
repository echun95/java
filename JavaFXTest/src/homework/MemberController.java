package homework;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberController implements Initializable{
	@FXML 
	private TextField memberId;
	@FXML 
	private TextField memberName;
	@FXML 
	private TextField memberTel;
	@FXML 
	private TextField memberAddr;
	@FXML 
	private Button btnAdd;
	@FXML 
	private Button btnMdify;
	@FXML 
	private Button btnDelete;
	@FXML 
	private Button btnOk;
	
	@FXML 
	private Button btnCancel;
	@FXML 
	private TableView<MemberVO> tb;
	@FXML 
	private TableColumn<MemberVO, String> colId;
	@FXML 
	private TableColumn<MemberVO, String>  colName;
	
	@FXML 
	private TableColumn<MemberVO, Integer>  colTel;
	
	@FXML 
	private TableColumn<MemberVO, String>  colAddr;
	
	ObservableList<MemberVO> MemberList ; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		colName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
		colTel.setCellValueFactory(new PropertyValueFactory<>("memberTel"));
		colAddr.setCellValueFactory(new PropertyValueFactory<>("memberAddr"));
		
		//들어가나 연습
		MemberList = FXCollections.observableArrayList(
				new MemberVO("a001","이재호",0101010100,"인천인천인천인천인천ㅇ"),
				new MemberVO("a001","이재호",0101010100,"인천인천인천인천인천ㅇ"),
				new MemberVO("a001","이재호",0101010100,"인천인천인천인천인천ㅇ")
				);
		
		tb.setItems(MemberList);
		btnOk.setDisable(true);
		btnCancel.setDisable(true);
		
		//추가버튼
		
		btnAdd.setOnAction(e->{
			if(memberId.getText().isEmpty() 
					|| memberName.getText().isEmpty()
					|| memberTel.getText().isEmpty()
					|| memberAddr.getText().isEmpty()
					) {
				
				errMsg("작업오류", "빈 항목이 있습니다.");
				return;
			}
			if(!Pattern.matches("^[0-9]+$", memberTel.getText())) {
				errMsg("데이터 오류", "나이는 정수형으로 입력하세요");
				memberTel.requestFocus(); // 해당 객체에 포커스 추가
				return;
			}
						btnOk.setDisable(false);
						btnCancel.setDisable(false);
						
			
		
		});
		
		// 수정
				btnMdify.setOnAction(e->{
					if(memberId.getText().isEmpty() 
							|| memberName.getText().isEmpty()
							|| memberTel.getText().isEmpty()
							|| memberAddr.getText().isEmpty()
							) {
						
						errMsg("작업오류", "빈 항목이 있습니다.");
						return;
					}
					if(!Pattern.matches("^[0-9]+$", memberTel.getText())) {
						errMsg("데이터 오류", "나이는 정수형으로 입력하세요");
						memberTel.requestFocus(); // 해당 객체에 포커스 추가
						return;
					}
					
					
					MemberList.set(tb.getSelectionModel().getSelectedIndex(), // 인덱스번호 찾기  , // 하나의 row를 선택하면 ui에서 선택된 표시가 되는데 
																						  // 이러한 것들을 관리하는게 SelectionModel()
							new MemberVO(memberId.getText(), // 수정할 데이터
							memberName.getText(),
							Integer.parseInt(memberTel.getText()),
							memberAddr.getText()));
					
					infoMsg("작업결과", memberName.getText()+ "님 정보를 수정했습니다.");
					
					memberId.clear();
					memberName.clear();
					memberTel.clear();
					memberAddr.clear();
				});
				
				
				btnDelete.setOnAction(e->{
					if(tb.getSelectionModel().isEmpty()) {
						errMsg("작업오류","삭제할 자료를 선택한 후 삭제하세요");
					}
					
					MemberList.remove(tb.getSelectionModel().getSelectedIndex());
					
					infoMsg("작업결과",memberName.getText() + "님 정보를 삭제했습니다.");
					
					memberId.clear();
					memberName.clear();
					memberTel.clear();
					memberAddr.clear();
					
				});
				
				
				btnOk.setOnAction(e->{
					

					MemberList.add(new MemberVO(memberId.getText(),
							memberName.getText(),
							Integer.parseInt(memberTel.getText()),
							memberAddr.getText()));
					
					infoMsg("작업결과", memberName.getText()+ "님 정보를 추가했습니다.");
					
					memberId.clear();
					memberName.clear();
					memberTel.clear();
					memberAddr.clear();
					
					
				});
				

				btnCancel.setOnAction(e->{
					memberId.clear();
					memberName.clear();
					memberTel.clear();
					memberAddr.clear();
				});
				
				tb.setOnMouseClicked(e->{
					//TableView에서 선택한 줄의 데이터를 얻는다.
					MemberVO member = tb.getSelectionModel().getSelectedItem();
					memberId.setText(member.getMemberId());
					memberName.setText(member.getMemberName());
					memberTel.setText(String.valueOf(member.getMemberTel()));
					memberAddr.setText(member.getMemberAddr());
				});
				
				
				 
			     
	}
	
		
			
		
		private void infoMsg(String headerText, String msg) {
			Alert errAlert = new Alert(AlertType.ERROR);
			errAlert.setTitle("오류");
			errAlert.setHeaderText(headerText);
			errAlert.setContentText(msg);
			errAlert.showAndWait();
			
		}

		private void errMsg(String headerText, String msg) {
			Alert errAlert = new Alert(AlertType.ERROR);
			errAlert.setTitle("오류");
			errAlert.setHeaderText(headerText);
			errAlert.setContentText(msg);
			errAlert.showAndWait();
	}
	
}