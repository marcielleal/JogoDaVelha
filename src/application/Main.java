package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		//Criar a janela
		PCJogador pc=new PCJogador("O",false);
		StringJogada jogada=new StringJogada();
		Validacao validacao=new Validacao();
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("FIM DE JOGO!");
		
		
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(0);
			grid.setVgap(0);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					Button bt=new Button();
					bt.setFont(new Font(30));
					bt.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							/* Impedir que o jogador jogue num botão já jogado ou
							 * que ele jogue depois do jogo ter acabado.
							 */
							if(bt.getText().equals("")&&!validacao.isIsfimDeJogo()){
								bt.setText(jogada.getProximaJogada());
								if(!validacao.validarEstagio(grid)){
									if(validacao.isEmpate())
										dialogoInfo.setHeaderText("EMPATE!");
									else
										dialogoInfo.setHeaderText("O jogador "+jogada.getUltimaJogada()+" venceu!");
									dialogoInfo.showAndWait();
									for(int i=0;i<9;i++)
										((Button)grid.getChildren().get(i)).setText("");
									
									validacao.reset();
									pc.reset();
									jogada.getProximaJogada();
									
								}else{
									pc.jogue(grid, jogada.getProximaJogada());
									if(!validacao.validarEstagio(grid)){
										if(validacao.isEmpate())
											dialogoInfo.setHeaderText("EMPATE!");
										else
											dialogoInfo.setHeaderText("O jogador "+jogada.getUltimaJogada()+" venceu!");
										dialogoInfo.showAndWait();
										
										for(int i=0;i<9;i++)
											((Button)grid.getChildren().get(i)).setText("");
										validacao.reset();
										pc.reset();
									}
								}
							}
						}
					});
					bt.setMinWidth(75);
					grid.add(bt, j, i);
				}
			}	
			
			Scene scene = new Scene(grid, 300, 275);
			primaryStage.setScene(scene);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
