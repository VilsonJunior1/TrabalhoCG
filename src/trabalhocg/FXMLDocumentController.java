/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import trabalhocg.codigos.Formas;
import trabalhocg.codigos.Poligono;
import trabalhocg.codigos.Arquivo;
import trabalhocg.codigos.Transformacoes2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import trabalhocg.codigos.Coloracao;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import trabalhocg.codigos.Faces;
import trabalhocg.codigos.FonteLuminosa;
import trabalhocg.codigos.Impressao3D;
import trabalhocg.codigos.LuzAmbiente;
import trabalhocg.codigos.Pintura;
import trabalhocg.codigos.Pontos;
import trabalhocg.codigos.ProjecaoPerspectiva;
import trabalhocg.codigos.Tela;
import trabalhocg.codigos.Transformacoes3D;

/**
 *
 * @author vilson
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button ButtonIrregular;

    @FXML
    private Button ButtonExclusion;

    @FXML
    private Button ButtonRetangulo;

    @FXML
    private Button ButtonPoligono;

    @FXML
    private Button ButtonSelection;

    @FXML
    private Button ButtonTriangulo;

    @FXML
    private Button ButtonHexagono;

    @FXML
    private Button ButtonPentagono;

    @FXML
    private Canvas monitor;
    
    @FXML
    private Canvas monitor4;

    @FXML
    private Canvas monitor2;

    @FXML
    private Canvas monitor3;
    
    @FXML
    private ColumnConstraints monitorWidth;

    @FXML
    private ColumnConstraints monitorWidth2;

    @FXML
    private RowConstraints monitorHeight;

    @FXML
    private RowConstraints monitorHeight2;
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    private Menu arquivo;
    
    @FXML
    private Menu ajuda;
    
    @FXML
    private MenuItem salvar;

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private MenuItem sobre;
    
    @FXML
    private MenuItem manual;

    @FXML
    private MenuItem abrir;
    
    @FXML
    private MenuItem novo;
    
    @FXML
    private Button ButtonCisalhamento;

    @FXML
    private Button ButtonEscala;

    @FXML
    private Button ButtonTranslada;
    
    @FXML
    private Button ButtonRotaciona;
    
    //@FXML
    //private ColorPicker colorPicker;
    
    //@FXML
    //private ColorPicker colorPickerContorno;
    
    @FXML
    private Slider ButtonSlider;

    //@FXML
    //private ChoiceBox<String> ListaCorPreenchimento;   
    
    @FXML
    private ChoiceBox<String> OcultacaoFaces;
    
    @FXML
    private ChoiceBox<String> Wireframe;
    
    @FXML
    private Label ValorSlider;
    
    @FXML
    private Button maximizar1;
    
    @FXML
    private Button maximizar2;

    @FXML
    private Button maximizar3;
    
    @FXML
    private Button maximizar4;
    
    @FXML
    private Button minimizar;
    
    @FXML
    private Text eixoXZ;

    @FXML
    private Text eixoXY;

    @FXML
    private Text visualiza;
    
    @FXML
    private Text eixoYZ;
    
    @FXML
    private Button ButtonExtrusao;
    
    @FXML
    private Button ButtonRevolucao;
    
    @FXML
    private Text textOcultacaoFaces;
    
    
    
    @FXML
    private Button buttonSalvarPol;

    /*@FXML
    private ChoiceBox<String> buttonFontesLuminosas;

    @FXML
    private Spinner<Double> buttonLx;

    @FXML
    private Spinner<Double> buttonLy;

    @FXML
    private Spinner<Double> buttonLz;

    @FXML
    private Spinner<Double> buttonIL;

    @FXML
    private Spinner<Double> buttonILA;*/

    @FXML
    private Button buttonFLuminosas;

    @FXML
    private Spinner<Double> buttonKar;

    @FXML
    private Spinner<Double> buttonKag;

    @FXML
    private Spinner<Double> buttonKab;

    @FXML
    private Spinner<Double> buttonKsr;

    @FXML
    private Spinner<Double> buttonKsg;

    @FXML
    private Spinner<Double> buttonKsb;

    @FXML
    private Spinner<Double> buttonKdr;

    @FXML
    private Spinner<Double> buttonKdg;

    @FXML
    private Spinner<Double> buttonKdb;

    @FXML
    private Spinner<Double> buttonN;
    
    @FXML
    private Text textKar;
    
    @FXML
    private Text textKag;
    
    @FXML
    private Text textKab;
    
    @FXML
    private Text textKsr;
    
    @FXML
    private Text textKsg;
    
    @FXML
    private Text textKsb;
    
    @FXML
    private Text textKdr;
    
    @FXML
    private Text textKdg;
    
    @FXML
    private Text textKdb;
    
    @FXML
    private Text textN;

    private int indiceFLuminosa = 0;
    
    private FonteLuminosa fontesLuminosa1 = new FonteLuminosa();
    
    private FonteLuminosa fontesLuminosa2 = new FonteLuminosa();
    
    private FonteLuminosa fontesLuminosa3 = new FonteLuminosa();
    
    private LuzAmbiente luzAmbiente = new LuzAmbiente();
    
    
    private int quantidadePontos;
    private int quantidadePontosNLados;
    
    private int angulo;
    
    //private Pane root = new Pane();
    private Formas forma = new Formas();
    
    private double X = 0, Y = 0, XO = 0, YO = 0;
    
    private Poligono auxilio = new Poligono();
    
    private boolean flag = false;
    
    private boolean quatroTelas = true;
    
    private int NTela = 0;
    
    private int indice = -1;
    
    private Tela Tela1 = new Tela();
    
    private Tela Tela2 = new Tela();
    
    private Tela Tela3 = new Tela();
    
    private Tela Tela4 = new Tela();
    
    private String tela;
    
    @FXML
    void actionFLuminosas(ActionEvent event) {
      
        /*if(indiceFLuminosa == 1)
        {
            fontesLuminosa1.setLx(buttonLx.getValue());
            fontesLuminosa1.setLy(buttonLy.getValue());
            fontesLuminosa1.setLz(buttonLz.getValue());
            
        }
        else if(indiceFLuminosa == 2)
        {
            fontesLuminosa2.setLx(buttonLx.getValue());
            fontesLuminosa2.setLy(buttonLy.getValue());
            fontesLuminosa2.setLz(buttonLz.getValue());
            
        }
        else if(indiceFLuminosa == 3)
        {
            fontesLuminosa3.setLx(buttonLx.getValue());
            fontesLuminosa3.setLy(buttonLy.getValue());
            fontesLuminosa3.setLz(buttonLz.getValue());
            
        }*/
        
        Dialog<Results2> dialog = new Dialog<>();
            
        dialog.setTitle("Fontes Luminosas");
        dialog.setHeaderText("Digite os dados para as fontes luminosas");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField textField = new TextField();
        //textField.setPromptText("ILA - R:");
        TextField textField2 = new TextField();
        //textField2.setPromptText("ILA - G:");
        TextField textField3 = new TextField();
        //textField3.setPromptText("ILA - B:");
        
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        TextField textField9 = new TextField();
        
        textField.setText(Double.toString(luzAmbiente.getILAR()));
        textField2.setText(Double.toString(luzAmbiente.getILAG()));
        textField3.setText(Double.toString(luzAmbiente.getILAB()));
        
        textField4.setText(Double.toString(fontesLuminosa1.getLx()));
        textField5.setText(Double.toString(fontesLuminosa1.getLy()));
        textField6.setText(Double.toString(fontesLuminosa1.getLz()));

        textField7.setText(Double.toString(fontesLuminosa1.getILR()));
        textField8.setText(Double.toString(fontesLuminosa1.getILG()));
        textField9.setText(Double.toString(fontesLuminosa1.getILB()));
        
        indiceFLuminosa = 1;
        
        ObservableList<String> options = FXCollections.observableArrayList("Fonte Luz 1", "Fonte Luz 2", "Fonte Luz 3");
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        
        List options2 = comboBox.getItems();
        comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
            
            if(options2.get(newSelected.intValue()).equals("Fonte Luz 1"))
            {
                textField4.setText(Double.toString(fontesLuminosa1.getLx()));
                textField5.setText(Double.toString(fontesLuminosa1.getLy()));
                textField6.setText(Double.toString(fontesLuminosa1.getLz()));
                
                textField7.setText(Double.toString(fontesLuminosa1.getILR()));
                textField8.setText(Double.toString(fontesLuminosa1.getILG()));
                textField9.setText(Double.toString(fontesLuminosa1.getILB()));
                
                indiceFLuminosa = 1;
            }
            else if(options2.get(newSelected.intValue()).equals("Fonte Luz 2"))
            {
                textField4.setText(Double.toString(fontesLuminosa2.getLx()));
                textField5.setText(Double.toString(fontesLuminosa2.getLy()));
                textField6.setText(Double.toString(fontesLuminosa2.getLz()));
                
                textField7.setText(Double.toString(fontesLuminosa2.getILR()));
                textField8.setText(Double.toString(fontesLuminosa2.getILG()));
                textField9.setText(Double.toString(fontesLuminosa2.getILB()));
                
                indiceFLuminosa = 2;
            }
            else if(options2.get(newSelected.intValue()).equals("Fonte Luz 3"))
            {
                textField4.setText(Double.toString(fontesLuminosa3.getLx()));
                textField5.setText(Double.toString(fontesLuminosa3.getLy()));
                textField6.setText(Double.toString(fontesLuminosa3.getLz()));
                
                textField7.setText(Double.toString(fontesLuminosa3.getILR()));
                textField8.setText(Double.toString(fontesLuminosa3.getILG()));
                textField9.setText(Double.toString(fontesLuminosa3.getILB()));
                
                indiceFLuminosa = 3;
            }
        } });

        grid.add(new Label("ILA - R:"), 0, 0);
        grid.add(textField, 1, 0);
        grid.add(new Label("ILA - G:"), 0, 1);
        grid.add(textField2, 1, 1);
        grid.add(new Label("ILA - B:"), 0, 2);
        grid.add(textField3, 1, 2);
        grid.add(new Label("Fonte Luminosa:"), 0, 3);
        grid.add(comboBox, 1, 3);
        grid.add(new Label("LX:"), 0, 4);
        grid.add(textField4, 1, 4);
        grid.add(new Label("LY:"), 0, 5);
        grid.add(textField5, 1, 5);
        grid.add(new Label("LZ:"), 0, 6);
        grid.add(textField6, 1, 6);
        grid.add(new Label("IL - R:"), 0, 7);
        grid.add(textField7, 1, 7);
        grid.add(new Label("IL - G:"), 0, 8);
        grid.add(textField8, 1, 8);
        grid.add(new Label("IL - B:"), 0, 9);
        grid.add(textField9, 1, 9);
        
        dialog.getDialogPane().setContent(grid);

        Platform.runLater(textField::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if(button == ButtonType.APPLY){
                return new Results2(Double.parseDouble(textField.getText()),
                Double.parseDouble(textField2.getText()), Double.parseDouble(textField3.getText()), Double.parseDouble(textField4.getText()), Double.parseDouble(textField5.getText()), Double.parseDouble(textField6.getText()), Double.parseDouble(textField7.getText()), Double.parseDouble(textField8.getText()), Double.parseDouble(textField9.getText()));
            }
            return null;
        });

        Optional<Results2> optionalResult = dialog.showAndWait();
        
        luzAmbiente.setILAR(optionalResult.get().ILAR);
        luzAmbiente.setILAG(optionalResult.get().ILAG);
        luzAmbiente.setILAB(optionalResult.get().ILAB);
        
        if(indiceFLuminosa == 1)
        {
            fontesLuminosa1.setILR(optionalResult.get().ILR);
            fontesLuminosa1.setILG(optionalResult.get().ILG);
            fontesLuminosa1.setILB(optionalResult.get().ILB);
            fontesLuminosa1.setLx(optionalResult.get().Lx);
            fontesLuminosa1.setLy(optionalResult.get().Ly);
            fontesLuminosa1.setLz(optionalResult.get().Lz);
        }
        else if(indiceFLuminosa == 2)
        {
            fontesLuminosa2.setILR(optionalResult.get().ILR);
            fontesLuminosa2.setILG(optionalResult.get().ILG);
            fontesLuminosa2.setILB(optionalResult.get().ILB);
            fontesLuminosa2.setLx(optionalResult.get().Lx);
            fontesLuminosa2.setLy(optionalResult.get().Ly);
            fontesLuminosa2.setLz(optionalResult.get().Lz);
        }
        else if(indiceFLuminosa == 3)
        {
            fontesLuminosa3.setILR(optionalResult.get().ILR);
            fontesLuminosa3.setILG(optionalResult.get().ILG);
            fontesLuminosa3.setILB(optionalResult.get().ILB);
            fontesLuminosa3.setLx(optionalResult.get().Lx);
            fontesLuminosa3.setLy(optionalResult.get().Ly);
            fontesLuminosa3.setLz(optionalResult.get().Lz);
        }
        
        ArrayList<FonteLuminosa> fontes = new ArrayList<>();
        fontes.add(fontesLuminosa1);
        fontes.add(fontesLuminosa2);
        fontes.add(fontesLuminosa3);
        forma.setFontes(fontes);
        forma.setLuz(luzAmbiente);
        
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
    }

    @FXML
    void actionSalvarPol(ActionEvent event) {
        
        if (indice != -1)
        {
            Poligono pol = new Poligono();
            
            //pol.setContorno(forma.getAllPoligonos().get(indice).getContorno());
            //pol.setCor(forma.getAllPoligonos().get(indice).getCor());
            pol.setKaR(buttonKar.getValue());
            pol.setKaG(buttonKag.getValue());
            pol.setKaB(buttonKab.getValue());
            pol.setKsR(buttonKsr.getValue());
            pol.setKsG(buttonKsg.getValue());
            pol.setKsB(buttonKsb.getValue());
            pol.setKdR(buttonKdr.getValue());
            pol.setKdG(buttonKdg.getValue());
            pol.setKdB(buttonKdb.getValue());
            pol.setN(buttonN.getValue());
            //pol.setContorno(forma.getAllPoligonos().get(indice).getContorno());
            //pol.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            pol.setListaP(forma.getAllPoligonos().get(indice).getListaP());
            pol.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            
            ArrayList<Poligono> aux = forma.getAllPoligonos();
            aux.set(indice, pol);
            forma.setAllPoligonos(aux);
            
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();

            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.impressao(gc, gc2, gc3, gc4);
        }
    }

    @FXML
    void actionTela1(MouseEvent event) {
        tela = "XY";
    }

    @FXML
    void actionTela2(MouseEvent event) {
        tela = "XZ";
    }

    @FXML
    void actionTela3(MouseEvent event) {
        tela = "YZ";
    }

    @FXML
    void actionTela4(MouseEvent event) {
        tela = "Perspectiva";
        
        quantidadePontos = -300;
    }
    
     @FXML
    void actionMaximizar1(ActionEvent event) {
        quatroTelas = false;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
        gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
        gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        //forma.impressao(gc, gc2, gc3, gc4);
        TelaMaximizada(1);
    }

    @FXML
    void actionMaximizar2(ActionEvent event) {
        quatroTelas = false;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
        gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
        gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        //forma.impressao(gc, gc2, gc3, gc4);
        TelaMaximizada(2);
    }

    @FXML
    void actionMaximizar3(ActionEvent event) {
        quatroTelas = false;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
        gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
        gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        //forma.impressao(gc, gc2, gc3, gc4);
        TelaMaximizada(3);
    }

    @FXML
    void actionMaximizar4(ActionEvent event) {
        quatroTelas = false;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
        gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
        gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        //forma.impressao(gc, gc2, gc3, gc4);
        TelaMaximizada(4);
    }
    
    @FXML
    void actionMinimizar(ActionEvent event) {
        quatroTelas = true;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        //forma.impressao(gc, gc2, gc3, gc4);
        QuatroTelas();
    }
    
    @FXML
    void actionButtonExtrusao(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
            
        }
        else
        {
            if(forma.getAllPoligonos().get(indice).getListaFaces().isEmpty() == false)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("OBJETO JA É 3D, NADA FOI ALTERADO");
                alert.showAndWait();
            }
            else
            {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Extrusão");
                dialog.setContentText("Digite o valor da profundidade Z: ");

                Optional<String> valor = dialog.showAndWait();

                int z = Integer.parseInt(valor.get());
                //System.out.println("valor de z: " + z);

                ArrayList<Poligono> novo;
                novo = forma.getAllPoligonos();
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();

                Transformacoes3D extrusao = new Transformacoes3D();

                Poligono ElNuevo = extrusao.Extrusao(novo.get(indice), z);

                novo.set(indice, ElNuevo);

                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.setAllPoligonos(novo);
                forma.impressao(gc, gc2, gc3, gc4);
                indice = -1;
            }
        }
    }

    @FXML
    void actionButtonRevolucao(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
            
        }
        else
        {
            if(forma.getAllPoligonos().get(indice).getListaFaces().isEmpty() == false)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("OBJETO JA É 3D, NADA FOI ALTERADO");
                alert.showAndWait();
            }
            else
            {
                Dialog<Results> dialog = new Dialog<>();
            
                dialog.setTitle("Revolução");
                dialog.setHeaderText("Digite os dados para a revolução");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField textField = new TextField();//Graus
                textField.setPromptText("Graus");
                TextField textField2 = new TextField();//Quantidade de Poligonos
                textField2.setPromptText("Quantidade Polígonos");
                ObservableList<Venue> options = FXCollections.observableArrayList(Venue.values());
                ComboBox<Venue> comboBox = new ComboBox<>(options);
                comboBox.getSelectionModel().selectFirst();

                grid.add(new Label("Graus:"), 0, 0);
                grid.add(textField, 1, 0);
                grid.add(new Label("Quantidade Polígonos:"), 0, 1);
                grid.add(textField2, 1, 1);
                grid.add(new Label("Eixo de rotação:"), 0, 2);
                grid.add(comboBox, 1, 2);

                dialog.getDialogPane().setContent(grid);

                Platform.runLater(textField::requestFocus);

                int Graus, QuantidadeDePontos;
                String Eixo;

                dialog.setResultConverter((ButtonType button) -> {
                    if(button == ButtonType.OK){
                        return new Results(textField.getText(),
                        textField2.getText(), comboBox.getValue());
                    }
                    return null;
                });

                Optional<Results> optionalResult = dialog.showAndWait();
                Graus = Integer.parseInt(optionalResult.get().graus);
                QuantidadeDePontos = Integer.parseInt(optionalResult.get().QuantidadeDePonto);
                Eixo = optionalResult.get().venue.name();

                optionalResult.ifPresent((Results results) -> {
                    System.out.println(
                            results.graus + " " + results.QuantidadeDePonto + " " + results.venue);
                });

                //System.out.println("2: " + Graus + " " + QuantidadeDePontos + " " + Eixo);

                //ate aq td certo

                ArrayList<Poligono> novo;
                novo = forma.getAllPoligonos();
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                novo.get(indice);
                Poligono ElNuevo = novo.get(indice);

                Transformacoes3D revolucao = new Transformacoes3D();

                Poligono novoPoligono;
                novoPoligono = revolucao.Revolucao(ElNuevo, Graus, QuantidadeDePontos, Eixo);
                //revolucao.Revolucao(ElNuevo, Graus, QuantidadeDePontos, Eixo);
                //novoPoligono = ElNuevo;

                novo.set(indice, novoPoligono);

                forma.setAllPoligonos(novo);

                //System.out.println("saiu revolucao tem " + forma.getAllPoligonos().get(indice).getListaFaces().size());

                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.setAllPoligonos(novo);
                forma.impressao(gc, gc2, gc3, gc4);
                indice = -1;
            }
        }
    }
    
    @FXML
    void actionButtonSelecao(ActionEvent event) {
        quantidadePontos = -2;
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
    }

    @FXML
    void actionButtonExclusao(ActionEvent event) {
        quantidadePontos = 0;
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            //alert.setContentText("I have a great message for you!");
            alert.showAndWait();
            
        }
        else
        {
            ArrayList<Poligono> novo;
            novo = forma.getAllPoligonos();
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            novo.remove(indice);
            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.setAllPoligonos(novo);
            forma.impressao(gc, gc2, gc3, gc4);
            indice = -1;
        }
    }

    @FXML
    void actionButtonTriangulo(ActionEvent event) {
        quantidadePontos = 3;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
    }

    @FXML
    void actionButtonRetangulo(ActionEvent event) {
        quantidadePontos = 4;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        if(flag==false)
        {
            click();
            click2();
            click3();
            flag=true;
        }
    }

    @FXML
    void actionButtonPentagono(ActionEvent event) {
        quantidadePontos = 5;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
    }

    @FXML
    void actionButtonHexagono(ActionEvent event) {
        quantidadePontos = 6;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
    }

    @FXML
    void actionButtonPoligonoRegular(ActionEvent event) {
        quantidadePontos = -8;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        /*if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }*/
        
        /*quantidadePontos = ButtonSlider.valueProperty().intValue();
        System.out.println("---------------------------------------");
        System.out.println("quantidade pontos" + quantidadePontos);
        System.out.println("---------------------------------------");*/
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
        
    }

    @FXML
    void actionButtonIrregular(ActionEvent event) {
        quantidadePontos = -3;
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        indice = -1;
        if(flag==false)
        {
            click();
            click2();
            click3();
            click4();
            flag=true;
        }
    }
    
    @FXML
    void actionAbrir(ActionEvent event) throws IOException {
        
        Arquivo arquivo = new Arquivo();
        
        FileChooser open = new FileChooser();
        open.setTitle("Selecione o Arquivo");
        //open.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        open.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".cg", "*.cg"));
        File end = open.showOpenDialog(null);
        end.getPath();
        String endereco;
        endereco = end.getPath();
        //System.out.println("endereco: " + endereco);
        arquivo.Abrir(endereco);
        
        forma.setAllPoligonos(arquivo.getAllPoligonos());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Abrir");
        alert.setHeaderText("ARQUIVO ABERTO COM SUCESSO");
        alert.showAndWait();
        
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        forma.impressao(gc, gc2, gc3, gc4);
        
    }

    @FXML
    void actionSalvar(ActionEvent event) throws IOException {
        
        Arquivo arquivo = new Arquivo();
        
        arquivo.setAllPoligonos(forma.getAllPoligonos());
        
        
        FileChooser salvar = new FileChooser();
        salvar.setTitle("Salve o Arquivo");
        //salvar.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        salvar.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".cg", "*.cg"));
        File end = salvar.showSaveDialog(null);
        end.getPath();
        String endereco;
        endereco = end.getPath();
        
        
        arquivo.salvar(endereco);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salvar");
        alert.setHeaderText("ARQUIVO SALVO COM SUCESSO");
        //alert.setContentText("I have a great message for you!");
        alert.showAndWait();
        
    }

    @FXML
    void actionSobre(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText("Desenvolvido por:");
        alert.setContentText("Bruno Henrique dos Santos Laier\nLarissa Santin\nVilson Norberto dos Santos Junior");
        alert.showAndWait();
    }
    
    @FXML
    void actionManual(ActionEvent event) {
        String desenhoPreDefinido, desenhoNlados, desenhoIrregular, excluirPoligono, salvarProjeto, abrirProjeto, transformacoes2D, colorir, colorir2;
        String manual;
        desenhoPreDefinido = "Para o desenho de poligonos pré-definidos que se encontram na parte esquerda da tela, " +
        "é necessário apenas clicar no ícone e em então na tela clicar e com o mouse e com o " +
        "mesmo clicado arrastar para ajustar o tamanho do polígono, ao soltar o clique o polígono " +
        "fica desenhado na tela.\n\n";
        desenhoNlados = "Para desenhar poligonos com mais lados selecione a opção que se parece com um circulo, ao " +
        "selecioná-la escolha quantos pontos possui seu poligono desejado com o slider, e então da mesma forma " +
        "que os polígonos pré-definidos desenhe na tela.\n\n";
        desenhoIrregular = "Para poligonos irregulares selecione sua opção e com os cliques do mouse desenhe a forma na tela.\n\n";
        excluirPoligono = "Para excluir um polígono é necessário selecionar o objeto com a ferramenta seleção e em seguida " +
        "clicar na ferramenta excluir.\n\n";
        salvarProjeto = "Para salvar seu projeto vá em arquivo, salvar e dê o nome ao seu projeto.\n\n";
        abrirProjeto = "Para abrir um projeto existente vá em arquivo, abrir e abra seu projeto.\n\n";
        transformacoes2D = "Para realizar uma transformação 2D basta selecionar o poligono e escolher qual a transformação, estas estão disponíveis nos botões a esquerda, que são elas: rotação, cisalhamento, translação e escala.\n\n";
        colorir = "Para colorir, caso o poligono já esteja desenhado é preciso seleciona-lo e então escolher a opção de colorir que se encontra na parte superior da tela, escolher a cor e marcar sim para informar que possui preenchimento e o contorno do objeto. Se quiser desenhar um objeto já colorido, basta selecionar a opção de colorir sim para informar que possui preenchimento e o poligono desejado e desenhar na tela. ";
        colorir2 = "Para remover a cor de um objeto selecione o objeto e clique em nao na opção preenchimento";
        manual = desenhoPreDefinido+desenhoNlados+desenhoIrregular+excluirPoligono+salvarProjeto+abrirProjeto+transformacoes2D+colorir+colorir2;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Manual");
        alert.setHeaderText("Como Usar:");
        alert.setContentText(manual);
        alert.showAndWait();
    }
    
    @FXML
    void actionNovo(ActionEvent event) {
        forma = new Formas();
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
    }
    
     @FXML
    void actionButtonRotaciona(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
        }
        else
        {
            angulo = 0;
            quantidadePontos = -7;
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
            
            if(flag==false)
            {
                click();
                click2();
                click3();
                click4();
                flag=true;
            }
        }
    }

    @FXML
    void actionCisalhamento(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
            
        }
        else
        {
            quantidadePontos = -6;
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
            
            Poligono novo = new Poligono();
            novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
            //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
            //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
            novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            forma.imprimePControleCisalhamento(gc, gc2, gc3, gc4, novo);
            
            if(flag==false)
            {
                click();
                click2();
                click3();
                click4();
                flag=true;
            }
        }
    }

    @FXML
    void actionButtonTranslada(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
            
        }
        else
        {
            quantidadePontos = -4;
            
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
            if(flag==false)
            {
                click();
                click2();
                click3();
                click4();
                flag=true;
            }
        }
    }

    @FXML
    void actionButtonEscala(ActionEvent event) {
        if (indice == -1)
        {
            //System.out.println("nao selecionado");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();
            
        }
        else
        {
            quantidadePontos = -5;
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
            
            Poligono novo = new Poligono();
            novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
            //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
            //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
            novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            forma.imprimePControleEscala(gc, gc2, gc3, gc4, novo);
            
            if(flag==false)
            {
                click();
                click2();
                click3();
                click4();
                flag=true;
            }
        }
    }
    
    /*@FXML
    void actionColorPicker(ActionEvent event) {  
        if(indice != -1)
        {
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            //Color contorno = colorPickerContorno.getValue();
            String color = colorPicker.getValue().toString();

            Pintura cor = new Pintura();

            Poligono aux = new Poligono();

            cor.setCor(color);
            cor.setListaPColorir(forma.getAllPoligonos().get(indice).getCor().getListaPColorir());
            cor.setListaFaces(forma.getAllPoligonos().get(indice).getCor().getListaFaces());
            String valor;
            valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
            if(valor.equals("Não"))
            {
                cor.setTemCor(forma.getAllPoligonos().get(indice).getCor().getTemCor());
            }
            else
            {
                cor.setTemCor(true);
            }
            

            aux.setContorno(forma.getAllPoligonos().get(indice).getContorno());
            aux.setCor(cor);
            aux.setListaP(forma.getAllPoligonos().get(indice).getListaP());
            aux.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            ArrayList<Poligono> nuevo;
            nuevo = forma.getAllPoligonos();

            nuevo.set(indice, aux);

            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.setAllPoligonos(nuevo);
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
        }
    }
    
    @FXML
    void actionColorPickerContorno(ActionEvent event) {
        if(indice != -1)
        {
            GraphicsContext gc = monitor.getGraphicsContext2D();
            GraphicsContext gc2 = monitor2.getGraphicsContext2D();
            GraphicsContext gc3 = monitor3.getGraphicsContext2D();
            GraphicsContext gc4 = monitor4.getGraphicsContext2D();
            
            String contorno = colorPickerContorno.getValue().toString();

            Poligono aux = new Poligono();
            
            aux.setContorno(contorno);
            aux.setCor(forma.getAllPoligonos().get(indice).getCor());
            aux.setListaP(forma.getAllPoligonos().get(indice).getListaP());
            aux.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
            ArrayList<Poligono> nuevo;
            nuevo = forma.getAllPoligonos();

            nuevo.set(indice, aux);

            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
            if (quatroTelas == true)
            {
                QuatroTelas();
            }
            else
            {
                TelaMaximizada(NTela);
            }
            forma.setAllPoligonos(nuevo);
            forma.impressao(gc, gc2, gc3, gc4);
            forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
        }
    }*/
    
    void click()
    {
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        monitor.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                if (quantidadePontos > 0)
                {
                    XO = e.getX();
                    YO = e.getY();
                }
                else if (quantidadePontos == -2)
                {
                    indice = -1;
                    XO = e.getX();
                    YO = e.getY();
                    if(forma.getTelaMaximizada() == false)
                    {
                        indice = forma.selecionaObjeto(XO-181, YO-133.75);
                    }
                    else
                    {
                        indice = forma.selecionaObjeto((XO-362)/2, (YO-267.5)/2);
                    }
                    if (indice != -1)
                    {
                        buttonKar.setVisible(true);
                        buttonKag.setVisible(true);
                        buttonKab.setVisible(true);
                        buttonKsr.setVisible(true);
                        buttonKsg.setVisible(true);
                        buttonKsb.setVisible(true);
                        buttonKdr.setVisible(true);
                        buttonKdg.setVisible(true);
                        buttonKdb.setVisible(true);
                        buttonN.setVisible(true);
                        buttonSalvarPol.setVisible(true);
                        textKar.setVisible(true);
                        textKag.setVisible(true);
                        textKab.setVisible(true);
                        textKsr.setVisible(true);
                        textKsg.setVisible(true);
                        textKsb.setVisible(true);
                        textKdr.setVisible(true);
                        textKdg.setVisible(true);
                        textKdb.setVisible(true);
                        textN.setVisible(true);
                        
                        DoubleSpinnerValueFactory value = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value.setAmountToStepBy(0.1);
                        buttonKar.setValueFactory(value);
                        DoubleSpinnerValueFactory value2 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value2.setAmountToStepBy(0.1);
                        buttonKag.setValueFactory(value2);
                        DoubleSpinnerValueFactory value3 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value3.setAmountToStepBy(0.1);
                        buttonKab.setValueFactory(value3);
                        DoubleSpinnerValueFactory value4 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value4.setAmountToStepBy(0.1);
                        buttonKsr.setValueFactory(value4);
                        DoubleSpinnerValueFactory value5 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value5.setAmountToStepBy(0.1);
                        buttonKsg.setValueFactory(value5);
                        DoubleSpinnerValueFactory value6 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value6.setAmountToStepBy(0.1);
                        buttonKsb.setValueFactory(value6);
                        DoubleSpinnerValueFactory value7 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value7.setAmountToStepBy(0.1);
                        buttonKdr.setValueFactory(value7);
                        DoubleSpinnerValueFactory value8 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value8.setAmountToStepBy(0.1);
                        buttonKdg.setValueFactory(value8);
                        DoubleSpinnerValueFactory value9 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value9.setAmountToStepBy(0.1);
                        buttonKdb.setValueFactory(value9);
                        DoubleSpinnerValueFactory value10 = new DoubleSpinnerValueFactory(0.0, 10.0);
                        value10.setAmountToStepBy(0.1);
                        buttonN.setValueFactory(value10);
                      
                        buttonKar.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaR());
                        buttonKag.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaG());
                        buttonKab.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaB());
                        buttonKsr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsR());
                        buttonKsg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsG());
                        buttonKsb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsB());
                        buttonKdr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdR());
                        buttonKdg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdG());
                        buttonKdb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdB());
                        buttonN.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getN());
                        
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                        /*if(forma.getAllPoligonos().get(indice).getCor().getTemCor() == true)
                        {
                            ListaCorPreenchimento.getSelectionModel().selectLast();
                            
                            colorPicker.setValue(Color.web(forma.getAllPoligonos().get(indice).getCor().getCor()));
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }
                        else
                        {
                            ListaCorPreenchimento.getSelectionModel().selectFirst();
                            
                            colorPicker.setValue(Color.GRAY);
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }*/
                    }
                    if (indice == -1)
                    {
                        buttonKar.setVisible(false);
                        buttonKag.setVisible(false);
                        buttonKab.setVisible(false);
                        buttonKsr.setVisible(false);
                        buttonKsg.setVisible(false);
                        buttonKsb.setVisible(false);
                        buttonKdr.setVisible(false);
                        buttonKdg.setVisible(false);
                        buttonKdb.setVisible(false);
                        buttonN.setVisible(false);
                        buttonSalvarPol.setVisible(false);
                        textKar.setVisible(false);
                        textKag.setVisible(false);
                        textKab.setVisible(false);
                        textKsr.setVisible(false);
                        textKsg.setVisible(false);
                        textKsb.setVisible(false);
                        textKdr.setVisible(false);
                        textKdg.setVisible(false);
                        textKdb.setVisible(false);
                        textN.setVisible(false);
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        //ListaCorPreenchimento.getSelectionModel().selectFirst();
                        
                        //colorPicker.setValue(Color.GRAY);
                        //colorPickerContorno.setValue(Color.BLACK);
                    }
                }
                else if (quantidadePontos == -3)
                {
                    if (e.getButton() == MouseButton.SECONDARY)
                    {
                        //String valor;
                        //valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                        
                        //String contorno = colorPickerContorno.getValue().toString();
                      
                        Poligono lista = new Poligono();

                        lista.setListaP(forma.getListaIrregular());
                        //lista.setContorno(contorno);
                        //lista.setCor(pintar);
                        forma.gravar(lista);
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        forma.setListaIrregular(new ArrayList<>());
                        
                        /*if(valor.equals("Não"))
                        {
                            Pintura pintar = new Pintura();
                            
                            pintar.setTemCor(false);
                            
                            Poligono lista = new Poligono();
                            
                            lista.setListaP(forma.getListaIrregular());
                            lista.setContorno(contorno);
                            lista.setCor(pintar);
                            forma.gravar(lista);
                            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            if (quatroTelas == true)
                            {
                                QuatroTelas();
                            }
                            else
                            {
                                TelaMaximizada(NTela);
                            }
                            forma.impressao(gc, gc2, gc3, gc4);
                            forma.setListaIrregular(new ArrayList<>());
                        }
                        else
                        {
                            String color = colorPicker.getValue().toString();
                            
                            Pintura pintar = new Pintura();
                            
                            pintar.setTemCor(true);
                            pintar.setCor(color);
                            
                            Poligono lista = new Poligono();
                            
                            lista.setListaP(forma.getListaIrregular());
                            lista.setContorno(contorno);
                            lista.setCor(pintar);
                            forma.gravar(lista);
                            gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                            if (quatroTelas == true)
                            {
                                QuatroTelas();
                            }
                            else
                            {
                                TelaMaximizada(NTela);
                            }
                            forma.impressao(gc, gc2, gc3, gc4);
                            forma.setListaIrregular(new ArrayList<>());
                        }*/
                    }
                    else
                    {
                        XO = e.getX();
                        YO = e.getY();
                        if(forma.getTelaMaximizada() == false)
                        {
                            forma.Irregular(XO-181, YO-133.75);
                        }
                        else
                        {
                            forma.Irregular((XO-362)/2, (YO-267.5)/2);
                        }
                        if (forma.getListaIrregular().size() > 1)
                        {
                            //String contorno = colorPickerContorno.getValue().toString();
                            
                            forma.imprimeIrregular(gc);
                        }
                    }
                }
                else if (quantidadePontos == -4)
                {
                    XO = e.getX();
                    YO = e.getY();
                }
                else if (quantidadePontos == -5)
                {
                    XO = e.getX();
                    YO = e.getY();
                    
                    Poligono novo = new Poligono();
                    novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                    //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
                    novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, novo);
                }
                else if (quantidadePontos == -6)
                {
                    XO = e.getX();
                    YO = e.getY();
                    
                    Poligono novo = new Poligono();
                    novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                    //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
                    novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                    
                    forma.imprimePControleCisalhamento(gc, gc2, gc3, gc4, novo);
                }
                else if (quantidadePontos == -8)
                {
                    XO = e.getX();
                    YO = e.getY();
                    quantidadePontosNLados = ButtonSlider.valueProperty().intValue();
                }
                else
                {
                    //
                }
            }
        });
        
        monitor.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                //gc.lineTo(event.getX(), event.getY());
                //gc.stroke();
                if(quantidadePontos > 0)
                {
                    //String valor;
                    //valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                    
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    //String contorno = colorPickerContorno.getValue().toString();
                    if(forma.getTelaMaximizada() == false)
                    {
                        forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos);
                    }
                    else
                    {
                        forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos);
                    }
                    
                    /*if(valor.equals("Não"))
                    {
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = event.getX();
                        Y = event.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos, contorno);
                        }
                        else
                        {
                            forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos, contorno);
                        }
                    }
                    else
                    {
                        Coloracao pintar = new Coloracao();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = event.getX();
                        Y = event.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos, contorno);
                        }
                        aux.setContorno(contorno);

                        //pintar.setPoligon(aux);

                        String color = colorPicker.getValue().toString();
                        //System.out.println("ColorIIIII = " + color);
                        //pintar.colorir(gc, color);
                        
                        if(forma.getTelaMaximizada() == false)
                        {
                            forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos, contorno);
                        }
                        else
                        {
                            forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos, contorno);
                        }
                    }*/
                }
                else if (quantidadePontos == -3)
                {
                    //X = event.getX();
                    //Y = event.getY();
                    //forma.Irregular(X, Y);
                }
                else if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    //Poligono nuevo = new Poligono();
                    //nuevo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //forma.imprimePControleEscala(gc, nuevo);
                    
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                }
                else if(quantidadePontos == -6)
                {
                    Transformacoes2D cisalhamento = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    //Poligono nuevo = new Poligono();
                    //nuevo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //forma.imprimePControleEscala(gc, nuevo);
                    
                    
                    Poligono novo = new Poligono();
                    cisalhamento.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = cisalhamento.Cisalhamento1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = cisalhamento.Cisalhamento1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                }
                else if(quantidadePontos == -8)
                {
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    //String contorno = colorPickerContorno.getValue().toString();
                    if(forma.getTelaMaximizada() == false)
                    {
                        forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados);
                    }
                    else
                    {
                        forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados);
                    }
                    
                    /*String valor;
                    valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                    if(valor.equals("Não"))
                    {
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = event.getX();
                        Y = event.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados, contorno);
                        }
                        else
                        {
                            forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados, contorno);
                        }
                    }
                    else
                    {
                        Coloracao pintar = new Coloracao();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = event.getX();
                        Y = event.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados, contorno);
                        }
                        aux.setContorno(contorno);

                        //pintar.setPoligon(aux);

                        String color = colorPicker.getValue().toString();
                        //System.out.println("ColorIIIII = " + color);
                        //pintar.colorir(gc, color);
                        
                        if(forma.getTelaMaximizada() == false)
                        {
                            forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados, contorno);
                        }
                        else
                        {
                            forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados, contorno);
                        }
                    }*/
                }
                else
                {
                    //
                }
            }
        });
        
        //forma.gravar(auxilio);
        //auxilio = new Poligono();
        monitor.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                
                if (quantidadePontos > 0)
                {
                    Poligono aux = new Poligono();

                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    //String contorno = colorPickerContorno.getValue().toString();
                    if(forma.getTelaMaximizada() == false)
                    {
                        aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos);
                    }
                    else
                    {
                        aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos);
                    }
                    //aux.setContorno(contorno);

                    //cor.setTemCor(false);

                    //aux.setCor(cor);

                    forma.gravar(aux);
                    
                    /*String valor;
                    valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                    if(valor.equals("Não"))
                    {
                        //System.out.println("Entrou nao");
                        Pintura cor = new Pintura();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = e.getX();
                        Y = e.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos, contorno);
                        }
                        aux.setContorno(contorno);
                        
                        cor.setTemCor(false);
                       
                        aux.setCor(cor);
                        
                        forma.gravar(aux);
                    }
                    else
                    {
                        //System.out.println("Entrou sim");
                        Pintura cor = new Pintura();
                        String color = colorPicker.getValue().toString();
                        Coloracao pintar = new Coloracao();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = e.getX();
                        Y = e.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontos, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontos, contorno);
                        }
                        aux.setContorno(contorno);
                        
                        cor.setTemCor(true);
                        cor.setCor(color);
                       
                        aux.setCor(cor);

                        //pintar.setPoligon(aux);
                        //pintar.colorir(gc, aux.getCor().getCor());
                        
                        forma.gravar(aux);
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                    }*/
                    //System.out.println("quantidade de pontos released " + quantidadePontos);
                }
                else if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    //GraphicsContext gc = monitor.getGraphicsContext2D();
                    //nuevo.remove(indice);
                    //nuevo.add(novo);
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, forma.getAllPoligonos().get(indice));
                }
                else if(quantidadePontos == -6)
                {
                    Transformacoes2D cisalhamento = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    cisalhamento.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = cisalhamento.Cisalhamento1(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = cisalhamento.Cisalhamento1(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
                    forma.imprimePControleCisalhamento(gc, gc2, gc3, gc4, forma.getAllPoligonos().get(indice));
                }
                else if (quantidadePontos == -8)
                {
                    Poligono aux = new Poligono();

                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    //String contorno = colorPickerContorno.getValue().toString();
                    if(forma.getTelaMaximizada() == false)
                    {
                        aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados);
                    }
                    else
                    {
                        aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados);
                    }
                    //aux.setContorno(contorno);

                    //cor.setTemCor(false);

                    //aux.setCor(cor);

                    forma.gravar(aux);
                    
                    /*String valor;
                    valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                    if(valor.equals("Não"))
                    {
                        //System.out.println("Entrou nao");
                        Pintura cor = new Pintura();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = e.getX();
                        Y = e.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados, contorno);
                        }
                        aux.setContorno(contorno);
                        
                        cor.setTemCor(false);
                       
                        aux.setCor(cor);
                        
                        forma.gravar(aux);
                    }
                    else
                    {
                        //System.out.println("Entrou sim");
                        Pintura cor = new Pintura();
                        String color = colorPicker.getValue().toString();
                        Coloracao pintar = new Coloracao();
                        Poligono aux = new Poligono();

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        X = e.getX();
                        Y = e.getY();
                        String contorno = colorPickerContorno.getValue().toString();
                        if(forma.getTelaMaximizada() == false)
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, XO-181, YO-133.75, X-181, Y-133.75, quantidadePontosNLados, contorno);
                        }
                        else
                        {
                            aux = forma.formas(gc, gc2, gc3, gc4, (XO-362)/2, (YO-267.5)/2, (X-362)/2, (Y-267.5)/2, quantidadePontosNLados, contorno);
                        }
                        aux.setContorno(contorno);
                        
                        cor.setTemCor(true);
                        cor.setCor(color);
                       
                        aux.setCor(cor);

                        //pintar.setPoligon(aux);
                        //pintar.colorir(gc, aux.getCor().getCor());
                        
                        forma.gravar(aux);
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                    }*/
                    //System.out.println("quantidade de pontos released " + quantidadePontosNLados);
                }
                else
                {
                    //
                }
            }
        });
        
        monitor.setOnScroll(ev->{
            if(quantidadePontos == -7)
            {
                if(ev.getDeltaY() > 0)
                {
                    angulo = 5;
                }
                else
                {
                    angulo = -5;
                }
                
                Transformacoes2D rotacao = new Transformacoes2D();
               
                Poligono novo = new Poligono();
                rotacao.setPoligon(forma.getAllPoligonos().get(indice));
                novo = rotacao.Rotaciona1(angulo);

                ArrayList<Poligono> nuevo;
                nuevo = forma.getAllPoligonos();

                //novo.setCor(nuevo.get(indice).getCor());
                //novo.setContorno(nuevo.get(indice).getContorno());
                nuevo.set(indice, novo);
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.setAllPoligonos(nuevo);
                forma.impressao(gc, gc2, gc3, gc4);
                forma.selecionado(gc, gc2, gc3, gc4, indice, "XY");
            }
        });
    }
    
    void click2()
    {
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        monitor2.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                //System.out.println("entrou tela 2");
                if (quantidadePontos == -2)
                {
                    indice = -1;
                    XO = e.getX();
                    YO = e.getY();
                    if(forma.getTelaMaximizada() == false)
                    {
                        indice = forma.selecionaObjeto2(XO-181, YO-133.75);
                    }
                    else
                    {
                        indice = forma.selecionaObjeto2((XO-362)/2, (YO-267.5)/2);
                    }
                    if (indice != -1)
                    {
                        buttonKar.setVisible(true);
                        buttonKag.setVisible(true);
                        buttonKab.setVisible(true);
                        buttonKsr.setVisible(true);
                        buttonKsg.setVisible(true);
                        buttonKsb.setVisible(true);
                        buttonKdr.setVisible(true);
                        buttonKdg.setVisible(true);
                        buttonKdb.setVisible(true);
                        buttonN.setVisible(true);
                        buttonSalvarPol.setVisible(true);
                        textKar.setVisible(true);
                        textKag.setVisible(true);
                        textKab.setVisible(true);
                        textKsr.setVisible(true);
                        textKsg.setVisible(true);
                        textKsb.setVisible(true);
                        textKdr.setVisible(true);
                        textKdg.setVisible(true);
                        textKdb.setVisible(true);
                        textN.setVisible(true);
                        
                        DoubleSpinnerValueFactory value = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value.setAmountToStepBy(0.1);
                        buttonKar.setValueFactory(value);
                        DoubleSpinnerValueFactory value2 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value2.setAmountToStepBy(0.1);
                        buttonKag.setValueFactory(value2);
                        DoubleSpinnerValueFactory value3 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value3.setAmountToStepBy(0.1);
                        buttonKab.setValueFactory(value3);
                        DoubleSpinnerValueFactory value4 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value4.setAmountToStepBy(0.1);
                        buttonKsr.setValueFactory(value4);
                        DoubleSpinnerValueFactory value5 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value5.setAmountToStepBy(0.1);
                        buttonKsg.setValueFactory(value5);
                        DoubleSpinnerValueFactory value6 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value6.setAmountToStepBy(0.1);
                        buttonKsb.setValueFactory(value6);
                        DoubleSpinnerValueFactory value7 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value7.setAmountToStepBy(0.1);
                        buttonKdr.setValueFactory(value7);
                        DoubleSpinnerValueFactory value8 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value8.setAmountToStepBy(0.1);
                        buttonKdg.setValueFactory(value8);
                        DoubleSpinnerValueFactory value9 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value9.setAmountToStepBy(0.1);
                        buttonKdb.setValueFactory(value9);
                        DoubleSpinnerValueFactory value10 = new DoubleSpinnerValueFactory(0.0, 10.0);
                        value10.setAmountToStepBy(0.1);
                        buttonN.setValueFactory(value10);
                      
                        buttonKar.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaR());
                        buttonKag.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaG());
                        buttonKab.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaB());
                        buttonKsr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsR());
                        buttonKsg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsG());
                        buttonKsb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsB());
                        buttonKdr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdR());
                        buttonKdg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdG());
                        buttonKdb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdB());
                        buttonN.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getN());
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
                        /*if(forma.getAllPoligonos().get(indice).getCor().getTemCor() == true)
                        {
                            ListaCorPreenchimento.getSelectionModel().selectLast();
                            
                            colorPicker.setValue(Color.web(forma.getAllPoligonos().get(indice).getCor().getCor()));
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }
                        else
                        {
                            ListaCorPreenchimento.getSelectionModel().selectFirst();
                            
                            colorPicker.setValue(Color.GRAY);
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }*/
                    }
                    if (indice == -1)
                    {
                        buttonKar.setVisible(false);
                        buttonKag.setVisible(false);
                        buttonKab.setVisible(false);
                        buttonKsr.setVisible(false);
                        buttonKsg.setVisible(false);
                        buttonKsb.setVisible(false);
                        buttonKdr.setVisible(false);
                        buttonKdg.setVisible(false);
                        buttonKdb.setVisible(false);
                        buttonN.setVisible(false);
                        buttonSalvarPol.setVisible(false);
                        textKar.setVisible(false);
                        textKag.setVisible(false);
                        textKab.setVisible(false);
                        textKsr.setVisible(false);
                        textKsg.setVisible(false);
                        textKsb.setVisible(false);
                        textKdr.setVisible(false);
                        textKdg.setVisible(false);
                        textKdb.setVisible(false);
                        textN.setVisible(false);
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        //ListaCorPreenchimento.getSelectionModel().selectFirst();
                        
                        //colorPicker.setValue(Color.GRAY);
                        //colorPickerContorno.setValue(Color.BLACK);
                    }
                }
                else if (quantidadePontos == -4)
                {
                    XO = e.getX();
                    YO = e.getY();
                }
                else if (quantidadePontos == -5)
                {
                    XO = e.getX();
                    YO = e.getY();
                    
                    Poligono novo = new Poligono();
                    novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                    //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
                    novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, novo);
                }
                else
                {
                    //
                }
            }
        });
        
        monitor2.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                //gc.lineTo(event.getX(), event.getY());
                //gc.stroke();
                if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao2(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao2(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    //Poligono nuevo = new Poligono();
                    //nuevo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //forma.imprimePControleEscala(gc, nuevo);
                    
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala2(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala2(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
                }
                else
                {
                    //
                }
            }
        });
        
        //forma.gravar(auxilio);
        //auxilio = new Poligono();
        monitor2.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                
                if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao2(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao2(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    //GraphicsContext gc = monitor.getGraphicsContext2D();
                    //nuevo.remove(indice);
                    //nuevo.add(novo);
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala2(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala2(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, forma.getAllPoligonos().get(indice));
                }
                else
                {
                    //
                }
            }
        });
        
        monitor2.setOnScroll(ev->{
            if(quantidadePontos == -7)
            {
                if(ev.getDeltaY() > 0)
                {
                    angulo = 5;
                }
                else
                {
                    angulo = -5;
                }
                
                Transformacoes2D rotacao = new Transformacoes2D();
               
                Poligono novo = new Poligono();
                rotacao.setPoligon(forma.getAllPoligonos().get(indice));
                novo = rotacao.Rotaciona2(angulo);

                ArrayList<Poligono> nuevo;
                nuevo = forma.getAllPoligonos();

                //novo.setCor(nuevo.get(indice).getCor());
                //novo.setContorno(nuevo.get(indice).getContorno());
                nuevo.set(indice, novo);
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.setAllPoligonos(nuevo);
                forma.impressao(gc, gc2, gc3, gc4);
                forma.selecionado(gc, gc2, gc3, gc4, indice, "XZ");
            }
        });
    }
    
    void click3()
    {
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        monitor3.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                //System.out.println("entrou tela 3");
                if (quantidadePontos == -2)
                {
                    indice = -1;
                    XO = e.getX();
                    YO = e.getY();
                    if(forma.getTelaMaximizada() == false)
                    {
                        indice = forma.selecionaObjeto3(XO-181, YO-133.75);
                    }
                    else
                    {
                        indice = forma.selecionaObjeto3((XO-362)/2, (YO-267.5)/2);
                    }
                    if (indice != -1)
                    {
                        buttonKar.setVisible(true);
                        buttonKag.setVisible(true);
                        buttonKab.setVisible(true);
                        buttonKsr.setVisible(true);
                        buttonKsg.setVisible(true);
                        buttonKsb.setVisible(true);
                        buttonKdr.setVisible(true);
                        buttonKdg.setVisible(true);
                        buttonKdb.setVisible(true);
                        buttonN.setVisible(true);
                        buttonSalvarPol.setVisible(true);
                        textKar.setVisible(true);
                        textKag.setVisible(true);
                        textKab.setVisible(true);
                        textKsr.setVisible(true);
                        textKsg.setVisible(true);
                        textKsb.setVisible(true);
                        textKdr.setVisible(true);
                        textKdg.setVisible(true);
                        textKdb.setVisible(true);
                        textN.setVisible(true);
                        
                        DoubleSpinnerValueFactory value = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value.setAmountToStepBy(0.1);
                        buttonKar.setValueFactory(value);
                        DoubleSpinnerValueFactory value2 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value2.setAmountToStepBy(0.1);
                        buttonKag.setValueFactory(value2);
                        DoubleSpinnerValueFactory value3 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value3.setAmountToStepBy(0.1);
                        buttonKab.setValueFactory(value3);
                        DoubleSpinnerValueFactory value4 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value4.setAmountToStepBy(0.1);
                        buttonKsr.setValueFactory(value4);
                        DoubleSpinnerValueFactory value5 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value5.setAmountToStepBy(0.1);
                        buttonKsg.setValueFactory(value5);
                        DoubleSpinnerValueFactory value6 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value6.setAmountToStepBy(0.1);
                        buttonKsb.setValueFactory(value6);
                        DoubleSpinnerValueFactory value7 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value7.setAmountToStepBy(0.1);
                        buttonKdr.setValueFactory(value7);
                        DoubleSpinnerValueFactory value8 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value8.setAmountToStepBy(0.1);
                        buttonKdg.setValueFactory(value8);
                        DoubleSpinnerValueFactory value9 = new DoubleSpinnerValueFactory(0.0, 1.0);
                        value9.setAmountToStepBy(0.1);
                        buttonKdb.setValueFactory(value9);
                        DoubleSpinnerValueFactory value10 = new DoubleSpinnerValueFactory(0.0, 10.0);
                        value10.setAmountToStepBy(0.1);
                        buttonN.setValueFactory(value10);
                      
                        buttonKar.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaR());
                        buttonKag.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaG());
                        buttonKab.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKaB());
                        buttonKsr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsR());
                        buttonKsg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsG());
                        buttonKsb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKsB());
                        buttonKdr.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdR());
                        buttonKdg.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdG());
                        buttonKdb.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getKdB());
                        buttonN.getValueFactory().setValue(forma.getAllPoligonos().get(indice).getN());
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
                        /*if(forma.getAllPoligonos().get(indice).getCor().getTemCor() == true)
                        {
                            ListaCorPreenchimento.getSelectionModel().selectLast();
                            
                            colorPicker.setValue(Color.web(forma.getAllPoligonos().get(indice).getCor().getCor()));
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }
                        else
                        {
                            ListaCorPreenchimento.getSelectionModel().selectFirst();
                            
                            colorPicker.setValue(Color.GRAY);
                            colorPickerContorno.setValue(Color.web(forma.getAllPoligonos().get(indice).getContorno()));
                        }*/
                    }
                    if (indice == -1)
                    {
                        buttonKar.setVisible(false);
                        buttonKag.setVisible(false);
                        buttonKab.setVisible(false);
                        buttonKsr.setVisible(false);
                        buttonKsg.setVisible(false);
                        buttonKsb.setVisible(false);
                        buttonKdr.setVisible(false);
                        buttonKdg.setVisible(false);
                        buttonKdb.setVisible(false);
                        buttonN.setVisible(false);
                        buttonSalvarPol.setVisible(false);
                        textKar.setVisible(false);
                        textKag.setVisible(false);
                        textKab.setVisible(false);
                        textKsr.setVisible(false);
                        textKsg.setVisible(false);
                        textKsb.setVisible(false);
                        textKdr.setVisible(false);
                        textKdg.setVisible(false);
                        textKdb.setVisible(false);
                        textN.setVisible(false);
                        
                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.impressao(gc, gc2, gc3, gc4);
                        //ListaCorPreenchimento.getSelectionModel().selectFirst();
                        
                        //colorPicker.setValue(Color.GRAY);
                        //colorPickerContorno.setValue(Color.BLACK);
                    }
                }
                else if (quantidadePontos == -4)
                {
                    XO = e.getX();
                    YO = e.getY();
                }
                else if (quantidadePontos == -5)
                {
                    XO = e.getX();
                    YO = e.getY();
                    
                    Poligono novo = new Poligono();
                    novo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //novo.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                    //novo.setCor(forma.getAllPoligonos().get(indice).getCor());
                    novo.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, novo);
                }
                else
                {
                    //
                }
            }
        });
        
        monitor3.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                //gc.lineTo(event.getX(), event.getY());
                //gc.stroke();
                if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao3(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao3(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = event.getX();
                    Y = event.getY();
                    
                    //Poligono nuevo = new Poligono();
                    //nuevo.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    //forma.imprimePControleEscala(gc, nuevo);
                    
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala3(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala3(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
                }
                else
                {
                    //
                }
            }
        });
        
        //forma.gravar(auxilio);
        //auxilio = new Poligono();
        monitor3.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                
                if(quantidadePontos == -4)
                {
                    Transformacoes2D translacao = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    translacao.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = translacao.Translacao3(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = translacao.Translacao3(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    //GraphicsContext gc = monitor.getGraphicsContext2D();
                    //nuevo.remove(indice);
                    //nuevo.add(novo);
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
                }
                else if(quantidadePontos == -5)
                {
                    Transformacoes2D escala = new Transformacoes2D();
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.impressao(gc, gc2, gc3, gc4);
                    X = e.getX();
                    Y = e.getY();
                    
                    Poligono novo = new Poligono();
                    escala.setPoligon(forma.getAllPoligonos().get(indice));
                    if(forma.getTelaMaximizada() == false)
                    {
                        novo = escala.Escala3(XO, X, YO, Y);
                    }
                    else
                    {
                        novo = escala.Escala3(XO/2, X/2, YO/2, Y/2);
                    }
                    forma.imprimeTranformacao(gc, gc2, gc3, gc4, novo);
                    
                    
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();
                    
                    //novo.setCor(nuevo.get(indice).getCor());
                    //novo.setContorno(nuevo.get(indice).getContorno());
                    nuevo.set(indice, novo);
                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
                    forma.imprimePControleEscala(gc, gc2, gc3, gc4, forma.getAllPoligonos().get(indice));
                }
                else
                {
                    //
                }
            }
        });
        
        monitor3.setOnScroll(ev->{
            if(quantidadePontos == -7)
            {
                if(ev.getDeltaY() > 0)
                {
                    angulo = 5;
                }
                else
                {
                    angulo = -5;
                }
                
                Transformacoes2D rotacao = new Transformacoes2D();
               
                Poligono novo = new Poligono();
                rotacao.setPoligon(forma.getAllPoligonos().get(indice));
                novo = rotacao.Rotaciona3(angulo);

                ArrayList<Poligono> nuevo;
                nuevo = forma.getAllPoligonos();

                //novo.setCor(nuevo.get(indice).getCor());
                //novo.setContorno(nuevo.get(indice).getContorno());
                nuevo.set(indice, novo);
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.setAllPoligonos(nuevo);
                forma.impressao(gc, gc2, gc3, gc4);
                forma.selecionado(gc, gc2, gc3, gc4, indice, "YZ");
            }
        });
    }
    
    void click4()//certa 
    {
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        
        monitor4.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                //System.out.println("entrou tela 4");
                
                XO = e.getX();
                YO = e.getY();
                
            }
        });
        
        monitor4.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("entrou aqui");
                X = event.getX();
                Y = event.getY();

                ProjecaoPerspectiva projecao = new ProjecaoPerspectiva();
                Pontos novo = new Pontos();

                if(forma.getTelaMaximizada() == false)
                {
                    novo = projecao.atualizaVRP(XO, YO, X, Y, forma.getVRP());
                }
                else
                {
                    novo = projecao.atualizaVRP(XO/2, YO/2, X/2, Y/2, forma.getVRP());
                }

                forma.setVRP(novo);

                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }

                forma.impressao(gc, gc2, gc3, gc4);


                XO = X;
                YO = Y;
            }
        });
        
        monitor4.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent e) {
                X = e.getX();
                Y = e.getY();

                ProjecaoPerspectiva projecao = new ProjecaoPerspectiva();
                Pontos novo = new Pontos();

                if(forma.getTelaMaximizada() == false)
                {
                    novo = projecao.atualizaVRP(XO, YO, X, Y, forma.getVRP());
                }
                else
                {
                    novo = projecao.atualizaVRP(XO/2, YO/2, X/2, Y/2, forma.getVRP());
                }

                forma.setVRP(novo);

                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());

                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }

                forma.impressao(gc, gc2, gc3, gc4);

                XO = X;
                YO = Y;
            }
        });
    }
    
    void desenhaTelasCoordenadasMinimizado(GraphicsContext gc)
    {
        gc.beginPath();
        gc.setStroke(Color.GREY);
        gc.setLineWidth(0.5);
        gc.moveTo(181, 0);
        gc.lineTo(181, 267.5);
        gc.stroke();
        
        gc.beginPath();
        gc.setStroke(Color.GREY);
        gc.setLineWidth(0.5);
        gc.moveTo(0, 133.75);
        gc.lineTo(362, 133.75);
        gc.stroke();
    }
    
    void desenhaTelasCoordenadasMaximizado(GraphicsContext gc)
    {
        gc.beginPath();
        gc.setStroke(Color.GREY);
        gc.setLineWidth(0.5);
        gc.moveTo(362, 0);
        gc.lineTo(362, 535);
        gc.stroke();
        
        gc.beginPath();
        gc.setStroke(Color.GREY);
        gc.setLineWidth(0.5);
        gc.moveTo(0, 267.5);
        gc.lineTo(724, 267.5);
        gc.stroke();
    }
    
    void QuatroTelas()
    {  
        forma.setTelaMaximizada(false);
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        
        monitor.setVisible(true);
        monitor.setWidth(362);
        monitor.setHeight(267.5);
        
        monitor2.setVisible(true);
        monitor2.setWidth(362);
        monitor2.setHeight(267.5);
        
        monitor3.setVisible(true);
        monitor3.setWidth(362);
        monitor3.setHeight(267.5);
        
        monitor4.setVisible(true);
        monitor4.setWidth(362);
        monitor4.setHeight(267.5);
        
        gridPane.setGridLinesVisible(true);
        monitorWidth.fillWidthProperty();
        monitorHeight.fillHeightProperty();
        monitorWidth2.fillWidthProperty();
        monitorHeight2.fillHeightProperty();
        
        desenhaTelasCoordenadasMinimizado(gc);
        desenhaTelasCoordenadasMinimizado(gc2);
        desenhaTelasCoordenadasMinimizado(gc3);
        //desenhaTelasCoordenadasMinimizado(gc4);
        
        Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/maximiza.png"));
        ImageView Maximizar = new ImageView();
        Maximizar.setImage(imageDecline);
        Maximizar.setFitHeight(10);
        Maximizar.setFitWidth(10);
        
        ImageView Maximizar2 = new ImageView();
        Maximizar2.setImage(imageDecline);
        Maximizar2.setFitHeight(10);
        Maximizar2.setFitWidth(10);
        
        ImageView Maximizar3 = new ImageView();
        Maximizar3.setImage(imageDecline);
        Maximizar3.setFitHeight(10);
        Maximizar3.setFitWidth(10);
        
        ImageView Maximizar4 = new ImageView();
        Maximizar4.setImage(imageDecline);
        Maximizar4.setFitHeight(10);
        Maximizar4.setFitWidth(10);
        
        minimizar.setVisible(false);
        
        maximizar1.setGraphic(Maximizar);
        maximizar1.setTranslateX(-15);
        maximizar1.setTranslateY(-252.5);
        maximizar1.setVisible(true);
        
        maximizar2.setGraphic(Maximizar2);
        maximizar2.setTranslateX(347);
        maximizar2.setTranslateY(-252.5);
        maximizar2.setVisible(true);
        
        maximizar3.setGraphic(Maximizar3);
        maximizar3.setTranslateX(-15);
        maximizar3.setTranslateY(15);
        maximizar3.setVisible(true);
        
        maximizar4.setGraphic(Maximizar4);
        maximizar4.setTranslateX(347);
        maximizar4.setTranslateY(15);
        maximizar4.setVisible(true);
        
        eixoXY.setTranslateX(-55);
        eixoXY.setTranslateY(-252.5);
        eixoXY.setVisible(true);
        
        eixoXZ.setTranslateX(307);
        eixoXZ.setTranslateY(-252.5);
        eixoXZ.setVisible(true);
        
        eixoYZ.setTranslateX(-55);
        eixoYZ.setTranslateY(15);
        eixoYZ.setVisible(true);
        
        visualiza.setTranslateX(307);
        visualiza.setTranslateY(15);
        visualiza.setVisible(true);
        
        forma.impressao(gc, gc2, gc3, gc4);
    }
    
    void TelaMaximizada(int tela)
    {
        forma.setTelaMaximizada(true);
        GraphicsContext gc = monitor.getGraphicsContext2D();
        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
        gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
        gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
        gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
        desenhaTelasCoordenadasMaximizado(gc);
        desenhaTelasCoordenadasMaximizado(gc2);
        desenhaTelasCoordenadasMaximizado(gc3);
        //desenhaTelasCoordenadasMaximizado(gc4);
        if(tela == 1)
        {   
            monitor.setWidth(724);
            monitor.setHeight(535);
            monitor.setVisible(true);
            monitor2.setVisible(false);
            monitor3.setVisible(false);
            monitor4.setVisible(false);
            gridPane.setGridLinesVisible(false);
            monitorWidth.fillWidthProperty();
            monitorHeight.fillHeightProperty();
            
            Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/maximiza.png"));
            ImageView Minimizar = new ImageView();
            Minimizar.setImage(imageDecline);
            Minimizar.setFitHeight(10);
            Minimizar.setFitWidth(10);

            minimizar.setGraphic(Minimizar);
            minimizar.setTranslateX(347);
            minimizar.setTranslateY(-252.5);
            minimizar.setVisible(true);
            
            maximizar1.setVisible(false);
            
            maximizar2.setVisible(false);

            maximizar3.setVisible(false);

            maximizar4.setVisible(false);

            eixoXY.setTranslateX(307);
            eixoXY.setTranslateY(-252.5);
            eixoXY.setVisible(true);

            eixoXZ.setVisible(false);

            eixoYZ.setVisible(false);

            visualiza.setVisible(false);
            
            desenhaTelasCoordenadasMaximizado(gc);
            forma.impressao(gc, gc2, gc3, gc4);
        }
        else if(tela == 2)
        {
            monitor2.setWidth(724);
            monitor2.setHeight(535);
            monitor2.setVisible(true);
            monitor.setVisible(false);
            monitor3.setVisible(false);
            monitor4.setVisible(false);
            gridPane.setGridLinesVisible(false);
            monitorWidth2.fillWidthProperty();
            monitorHeight2.fillHeightProperty();
            
            
            Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/maximiza.png"));
            ImageView Minimizar = new ImageView();
            Minimizar.setImage(imageDecline);
            Minimizar.setFitHeight(10);
            Minimizar.setFitWidth(10);

            minimizar.setGraphic(Minimizar);
            minimizar.setTranslateX(347);
            minimizar.setTranslateY(-252.5);
            minimizar.setVisible(true);
            
            maximizar1.setVisible(false);
            
            maximizar2.setVisible(false);

            maximizar3.setVisible(false);

            maximizar4.setVisible(false);

            eixoXY.setVisible(false);

            eixoXZ.setTranslateX(307);
            eixoXZ.setTranslateY(-252.5);
            eixoXZ.setVisible(true);

            eixoYZ.setVisible(false);

            visualiza.setVisible(false);
            
            desenhaTelasCoordenadasMaximizado(gc2);
            forma.impressao(gc, gc2, gc3, gc4);
        }
        else if(tela == 3)
        {
            monitor3.setWidth(724);
            monitor3.setHeight(535);
            monitor3.setVisible(true);
            monitor2.setVisible(false);
            monitor.setVisible(false);
            monitor4.setVisible(false);
            gridPane.setGridLinesVisible(false);
            monitorWidth.fillWidthProperty();
            monitorHeight.fillHeightProperty();
            
            
            Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/maximiza.png"));
            ImageView Minimizar = new ImageView();
            Minimizar.setImage(imageDecline);
            Minimizar.setFitHeight(10);
            Minimizar.setFitWidth(10);

            minimizar.setGraphic(Minimizar);
            minimizar.setTranslateX(347);
            minimizar.setTranslateY(-252.5);
            minimizar.setVisible(true);
            
            maximizar1.setVisible(false);
            
            maximizar2.setVisible(false);

            maximizar3.setVisible(false);

            maximizar4.setVisible(false);

            eixoXY.setVisible(false);

            eixoXZ.setVisible(false);

            eixoYZ.setTranslateX(307);
            eixoYZ.setTranslateY(-252.5);
            eixoYZ.setVisible(true);

            visualiza.setVisible(false);
            
            desenhaTelasCoordenadasMaximizado(gc3);
            forma.impressao(gc, gc2, gc3, gc4);
        }
        else if(tela == 4)
        {
            monitor4.setWidth(724);
            monitor4.setHeight(535);
            monitor4.setVisible(true);
            monitor2.setVisible(false);
            monitor3.setVisible(false);
            monitor.setVisible(false);
            gridPane.setGridLinesVisible(false);
            monitorWidth2.fillWidthProperty();
            monitorHeight2.fillHeightProperty();
            
            
            Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/maximiza.png"));
            ImageView Minimizar = new ImageView();
            Minimizar.setImage(imageDecline);
            Minimizar.setFitHeight(10);
            Minimizar.setFitWidth(10);

            minimizar.setGraphic(Minimizar);
            minimizar.setTranslateX(347);
            minimizar.setTranslateY(-252.5);
            minimizar.setVisible(true);
            
            maximizar1.setVisible(false);
            
            maximizar2.setVisible(false);

            maximizar3.setVisible(false);

            maximizar4.setVisible(false);

            eixoXY.setVisible(false);

            eixoXZ.setVisible(false);

            eixoYZ.setVisible(false);

            visualiza.setTranslateX(307);
            visualiza.setTranslateY(-252.5);
            visualiza.setVisible(true);
            
            //desenhaTelasCoordenadasMaximizado(gc4);
            forma.impressao(gc, gc2, gc3, gc4);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fontesLuminosa2.setILR(0);
        fontesLuminosa2.setILG(0);
        fontesLuminosa2.setILB(0);
        fontesLuminosa3.setILR(0);
        fontesLuminosa3.setILG(0);
        fontesLuminosa3.setILB(0);
        
        ArrayList<FonteLuminosa> fontes = new ArrayList<>();
        fontes.add(fontesLuminosa1);
        fontes.add(fontesLuminosa2);
        fontes.add(fontesLuminosa3);
        forma.setFontes(fontes);
        forma.setLuz(luzAmbiente);
        
        FonteLuminosa fonte = new FonteLuminosa();
        
        click4();
        
        Tela1.setX1(0);
        Tela1.setY1(0);
        Tela1.setX2(362);
        Tela1.setY2(0);
        Tela1.setX3(0);
        Tela1.setY3(267.5);
        Tela1.setX4(362);
        Tela1.setY4(267.5);
        
        Tela2.setX1(362);
        Tela2.setY1(0);
        Tela2.setX2(724);
        Tela2.setY2(0);
        Tela2.setX3(362);
        Tela2.setY3(267.5);
        Tela2.setX4(724);
        Tela2.setY4(267.5);
        
        Tela3.setX1(0);
        Tela3.setY1(267.5);
        Tela3.setX2(362);
        Tela3.setY2(267.5);
        Tela3.setX3(0);
        Tela3.setY3(535);
        Tela3.setX4(362);
        Tela3.setY4(535);
        
        Tela4.setX1(362);
        Tela4.setY1(267.5);
        Tela4.setX2(724);
        Tela4.setY2(267.5);
        Tela4.setX3(362);
        Tela4.setY3(535);
        Tela4.setX4(724);
        Tela4.setY4(535);
        
        if (quatroTelas == true)
        {
            QuatroTelas();
        }
        else
        {
            TelaMaximizada(NTela);
        }
        
        
        //colorPicker.setStyle("-fx-color-label-visible: false ;");
        
        /*ObservableList<String> escolhas = FXCollections.observableArrayList("Não", "Sim");
        ListaCorPreenchimento.setItems(escolhas);
        ListaCorPreenchimento.getSelectionModel().selectFirst();
        
        List options = ListaCorPreenchimento.getItems();
        ListaCorPreenchimento.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
            
            if(options.get(newSelected.intValue()).equals("Não"))
            {
                if(indice != -1)
                {
                    GraphicsContext gc = monitor.getGraphicsContext2D();
                    GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                    GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                    GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                    Pintura cor = new Pintura();

                    Poligono aux = new Poligono();

                    cor.setCor(forma.getAllPoligonos().get(indice).getCor().getCor());
                    cor.setListaPColorir(forma.getAllPoligonos().get(indice).getCor().getListaPColorir());
                    cor.setListaFaces(forma.getAllPoligonos().get(indice).getCor().getListaFaces());
                    cor.setTemCor(false);
                    
                    aux.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                    aux.setCor(cor);
                    aux.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                    aux.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                    ArrayList<Poligono> nuevo;
                    nuevo = forma.getAllPoligonos();

                    nuevo.set(indice, aux);

                    gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                    if (quatroTelas == true)
                    {
                        QuatroTelas();
                    }
                    else
                    {
                        TelaMaximizada(NTela);
                    }
                    forma.setAllPoligonos(nuevo);
                    forma.impressao(gc, gc2, gc3, gc4);
                    forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
                }
            }
            else if(options.get(newSelected.intValue()).equals("Sim"))
            {
                if(indice != -1)
                {
                    if(forma.getAllPoligonos().get(indice).getCor().getTemCor() == false)
                    {
                        GraphicsContext gc = monitor.getGraphicsContext2D();
                        GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                        GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                        GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                    
                        String color = colorPicker.getValue().toString();

                        Pintura cor = new Pintura();

                        Poligono aux = new Poligono();

                        cor.setCor(color);
                        cor.setListaPColorir(forma.getAllPoligonos().get(indice).getCor().getListaPColorir());
                        cor.setListaFaces(forma.getAllPoligonos().get(indice).getCor().getListaFaces());

                        String valor;
                        valor = ListaCorPreenchimento.getSelectionModel().getSelectedItem();
                        cor.setTemCor(true);

                        aux.setContorno(forma.getAllPoligonos().get(indice).getContorno());
                        aux.setCor(cor);
                        aux.setListaP(forma.getAllPoligonos().get(indice).getListaP());
                        aux.setListaFaces(forma.getAllPoligonos().get(indice).getListaFaces());
                        ArrayList<Poligono> nuevo;
                        nuevo = forma.getAllPoligonos();

                        nuevo.set(indice, aux);

                        gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc2.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc3.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        gc4.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                        if (quatroTelas == true)
                        {
                            QuatroTelas();
                        }
                        else
                        {
                            TelaMaximizada(NTela);
                        }
                        forma.setAllPoligonos(nuevo);
                        forma.impressao(gc, gc2, gc3, gc4);
                        forma.selecionado(gc, gc2, gc3, gc4, indice, tela);
                    }
                }
            }
        } });
        colorPicker.setValue(Color.GRAY);
        colorPickerContorno.setValue(Color.BLACK);*/
        
        ObservableList<String> escolhas2 = FXCollections.observableArrayList("Não", "Sim");
        OcultacaoFaces.setItems(escolhas2);
        OcultacaoFaces.getSelectionModel().selectFirst();
        
        
        List options2 = OcultacaoFaces.getItems();
        OcultacaoFaces.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
            
            if(options2.get(newSelected.intValue()).equals("Não"))
            {
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                
                forma.setOcultacaoFaces(false);
                
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
                gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
                gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.impressao(gc, gc2, gc3, gc4);
            }
            else if(options2.get(newSelected.intValue()).equals("Sim"))
            {
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                
                forma.setOcultacaoFaces(true);
                //System.out.println("entrou oculta");
                
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
                gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
                gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.impressao(gc, gc2, gc3, gc4);
            }
        } });
        
        OcultacaoFaces.setVisible(false);
        textOcultacaoFaces.setVisible(false);
        
        //Para fazer o wireframe
        ObservableList<String> escolhas3 = FXCollections.observableArrayList("Não", "Sim");
        Wireframe.setItems(escolhas3);
        Wireframe.getSelectionModel().selectFirst();
        
        
        List options3 = Wireframe.getItems();
        Wireframe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
            
            if(options3.get(newSelected.intValue()).equals("Não"))
            {
                OcultacaoFaces.setVisible(false);
                textOcultacaoFaces.setVisible(false);
                
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                
                forma.setWireframe(false);
                
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
                gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
                gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.impressao(gc, gc2, gc3, gc4);
            }
            else if(options3.get(newSelected.intValue()).equals("Sim"))
            {
                OcultacaoFaces.setVisible(true);
                textOcultacaoFaces.setVisible(true);
                
                GraphicsContext gc = monitor.getGraphicsContext2D();
                GraphicsContext gc2 = monitor2.getGraphicsContext2D();
                GraphicsContext gc3 = monitor3.getGraphicsContext2D();
                GraphicsContext gc4 = monitor4.getGraphicsContext2D();
                
                forma.setWireframe(true);
                //System.out.println("entrou wireframe");
                
                gc.clearRect(0, 0, monitor.getWidth(), monitor.getHeight());
                gc2.clearRect(0, 0, monitor2.getWidth(), monitor2.getHeight());
                gc3.clearRect(0, 0, monitor3.getWidth(), monitor3.getHeight());
                gc4.clearRect(0, 0, monitor4.getWidth(), monitor4.getHeight());
                if (quatroTelas == true)
                {
                    QuatroTelas();
                }
                else
                {
                    TelaMaximizada(NTela);
                }
                forma.impressao(gc, gc2, gc3, gc4);
            }
        } });
        //Termino do wireframe
        
        //Para fazer o fontes luminosas
        buttonKar.setVisible(false);
        buttonKag.setVisible(false);
        buttonKab.setVisible(false);
        buttonKsr.setVisible(false);
        buttonKsg.setVisible(false);
        buttonKsb.setVisible(false);
        buttonKdr.setVisible(false);
        buttonKdg.setVisible(false);
        buttonKdb.setVisible(false);
        buttonN.setVisible(false);
        buttonSalvarPol.setVisible(false);
        textKar.setVisible(false);
        textKag.setVisible(false);
        textKab.setVisible(false);
        textKsr.setVisible(false);
        textKsg.setVisible(false);
        textKsb.setVisible(false);
        textKdr.setVisible(false);
        textKdg.setVisible(false);
        textKdb.setVisible(false);
        textN.setVisible(false);
        
        
        /*ObservableList<String> escolhas4 = FXCollections.observableArrayList("Fonte Luminosa 1", "Fonte Luminosa 2", "Fonte Luminosa 3");
        buttonFontesLuminosas.setItems(escolhas4);
        buttonFontesLuminosas.getSelectionModel().selectFirst();
        
        List options4 = buttonFontesLuminosas.getItems();
        buttonFontesLuminosas.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
            
            if(options4.get(newSelected.intValue()).equals("Fonte Luminosa 1"))
            {
                indiceFLuminosa = 1;
                buttonLx.getValueFactory().setValue(fontesLuminosa1.getLx());
                buttonLy.getValueFactory().setValue(fontesLuminosa1.getLy());
                buttonLz.getValueFactory().setValue(fontesLuminosa1.getLz());
                
            }
            else if(options4.get(newSelected.intValue()).equals("Fonte Luminosa 2"))
            {
                indiceFLuminosa = 2;
                buttonLx.getValueFactory().setValue(fontesLuminosa2.getLx());
                buttonLy.getValueFactory().setValue(fontesLuminosa2.getLy());
                buttonLz.getValueFactory().setValue(fontesLuminosa2.getLz());
                
            }
            else if(options4.get(newSelected.intValue()).equals("Fonte Luminosa 3"))
            {
                indiceFLuminosa = 3;
                buttonLx.getValueFactory().setValue(fontesLuminosa3.getLx());
                buttonLy.getValueFactory().setValue(fontesLuminosa3.getLy());
                buttonLz.getValueFactory().setValue(fontesLuminosa3.getLz());
                
            }
        } });*/
        //Termino do fontes luminosas
        
        Image imageDecline = new Image(getClass().getResourceAsStream("Imagens/selecao.png"));
        ImageView selecao = new ImageView();
        selecao.setImage(imageDecline);
        selecao.setFitHeight(18);
        selecao.setFitWidth(12);
        ButtonSelection.setGraphic(selecao);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/exclusao.png"));
        ImageView exclusao = new ImageView();
        exclusao.setImage(imageDecline);
        exclusao.setFitHeight(18);
        exclusao.setFitWidth(15);
        ButtonExclusion.setGraphic(exclusao);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/triangulo.png"));
        ImageView triangulo = new ImageView();
        triangulo.setImage(imageDecline);
        triangulo.setFitHeight(18);
        triangulo.setFitWidth(18);
        ButtonTriangulo.setGraphic(triangulo);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/quadrado.png"));
        ImageView retangulo = new ImageView();
        retangulo.setImage(imageDecline);
        retangulo.setFitHeight(18);
        retangulo.setFitWidth(18);
        ButtonRetangulo.setGraphic(retangulo);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/pentagono.png"));
        ImageView pentagono = new ImageView();
        pentagono.setImage(imageDecline);
        pentagono.setFitHeight(18);
        pentagono.setFitWidth(18);
        ButtonPentagono.setGraphic(pentagono);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/hexagono.png"));
        ImageView hexagono = new ImageView();
        hexagono.setImage(imageDecline);
        hexagono.setFitHeight(18);
        hexagono.setFitWidth(18);
        ButtonHexagono.setGraphic(hexagono);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/nLados.png"));
        ImageView Nlados = new ImageView();
        Nlados.setImage(imageDecline);
        Nlados.setFitHeight(18);
        Nlados.setFitWidth(18);
        ButtonPoligono.setGraphic(Nlados);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/irregular.png"));
        ImageView irregular = new ImageView();
        irregular.setImage(imageDecline);
        irregular.setFitHeight(18);
        irregular.setFitWidth(18);
        ButtonIrregular.setGraphic(irregular);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/rotacao.png"));
        ImageView rotacao = new ImageView();
        rotacao.setImage(imageDecline);
        rotacao.setFitHeight(18);
        rotacao.setFitWidth(18);
        ButtonRotaciona.setGraphic(rotacao);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/cisalhamento.png"));
        ImageView cisalhamento = new ImageView();
        cisalhamento.setImage(imageDecline);
        cisalhamento.setFitHeight(18);
        cisalhamento.setFitWidth(18);
        ButtonCisalhamento.setGraphic(cisalhamento);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/translacao.png"));
        ImageView translacao = new ImageView();
        translacao.setImage(imageDecline);
        translacao.setFitHeight(18);
        translacao.setFitWidth(18);
        ButtonTranslada.setGraphic(translacao);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/escala.png"));
        ImageView escala = new ImageView();
        escala.setImage(imageDecline);
        escala.setFitHeight(18);
        escala.setFitWidth(18);
        ButtonEscala.setGraphic(escala);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/extrusao.png"));
        ImageView extrusao = new ImageView();
        extrusao.setImage(imageDecline);
        extrusao.setFitHeight(18);
        extrusao.setFitWidth(18);
        ButtonExtrusao.setGraphic(extrusao);
        
        imageDecline = new Image(getClass().getResourceAsStream("Imagens/revolucao.png"));
        ImageView revolucao = new ImageView();
        revolucao.setImage(imageDecline);
        revolucao.setFitHeight(18);
        revolucao.setFitWidth(18);
        ButtonRevolucao.setGraphic(revolucao);
        
        ButtonSlider.valueProperty().addListener((obs, oldval, newVal) -> ButtonSlider.setValue(newVal.intValue()));
        ValorSlider.textProperty().bind(
          ButtonSlider.valueProperty().asString()
        );
    }    
    
}

enum Venue {X, Y, Z}

class Results {
    String graus;
    String QuantidadeDePonto;
    Venue venue;
    
    public Results(String name, String QuantidadeDePonto, Venue venue)
    {
        this.graus = name;
        this.QuantidadeDePonto = QuantidadeDePonto;
        this.venue = venue;
    }
}

enum Venue2 {Luz1, Luz2, Luz3}

class Results2 {
    
    double ILAR;
    double ILAG;
    double ILAB;
    double Lx;
    double Ly;
    double Lz;
    double ILR;
    double ILG;
    double ILB;

    public Results2(double ILAR, double ILAG, double ILAB, double Lx, double Ly, double Lz, double ILR, double ILG, double ILB) {
        this.ILAR = ILAR;
        this.ILAG = ILAG;
        this.ILAB = ILAB;
        this.Lx = Lx;
        this.Ly = Ly;
        this.Lz = Lz;
        this.ILR = ILR;
        this.ILG = ILG;
        this.ILB = ILB;
    }
    
}