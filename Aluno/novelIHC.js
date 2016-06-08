/*
    Fill this array with a list of names of images
    to be pre-loaded.
*/
var preload = [

];

/*
    This section pre-loads your images.
    Don't change it unless you know what you're doing.
*/
var preloadObj = new Array(preload.length);
for (var i = 0; i < preload.length; i++)
{
    preloadObj[i] = new Image();
    preloadObj[i].src = preload[i];
}

/* Declare variables for characters, positions, and text blocks here */
var script = [];                 // this variable will hold your script
var respostas = [];
var contador_respostas = 0;
var narrator;
var inputArea;
var imagemJSON;

// ATRIBUTOS DO ALUNO
sessionStorage.setItem("equilibrioEmocional",0);
sessionStorage.setItem("trabalhoEquipe",0);
sessionStorage.setItem("resiliencia",0);
sessionStorage.setItem("visaoFutura",0);
sessionStorage.setItem("gestaoTempo",0);
sessionStorage.setItem("comunicacao",0);
sessionStorage.setItem("void",0);

/*
	FUNCAO PRINCIPAL
*/
function prepareNovel(){
		var inicio = [];
		var fim = [];
		var pergunta_alternativas = [];
		
		novel.imagePath = "http://i.imgur.com/"; // path to your image directory
		novel.audioPath = ""; // path to your audio directory	
		
		//CAIXA DE TEXTO
		inputArea = new Input('nome',
		{
			position: new Position(0.2, 0.5),
			width: 0.5,
			text: ""		
		});
		
		//NARRADOR SAO OS TEXTOS ONDE APARECE AS MENSAGENS DA NOVEL
		narrator = new Character("Pesquisadora da FATEC");	
		
		//IMAGEMJSON E O QUE CARREGA A IMAGEM NA NOVEL
		imagemJSON = new Character("Ed the Salesman",
			{
				color: "#ff0",
				position: new Position(0.63, 0.6, 1, 0)
			}
		);	
		
		// AJAX FAZ FAZ A REQUISICAO PARA RECEBER OS VALORES DO GOOGLE SHEETS
		$.ajax({
			url: "https://spreadsheets.google.com/feeds/list/1f_d1AaD23JX8z51g_ZELn8n4gKKZKR7jXOdf4zyxdR8/od6/public/basic?alt=json",
			type: "GET",
			dataType: "json",
			async: false,
			timeout: 10000,
			error: function(data) { 
				console.log("ERROR");
			}
			,
			success: function(data) { 				
				linha = 0;	// VARIAVEL QUE CONTROLA AS LINHAS DE PERGUNTAS E ALTERNATIVAS
				categoriaDaPergunta = ""; // LISTA PARA DETERMINAR QUAL É O TIPO DE PERGUNTA
				dicionarioMeio = {};
				eUmaCategoria = true; // Verifica se a posição é uma categoria de pergunta ou se é o peso da mesma
				$.each(data.feed.entry, function(data, value) {
					if (linha%9 == 0 && linha!=0){ // VERIFICA SE A LINHA ATUAL É UMA PERGUNTA, SIGNIFICANDO QUE A PERGUNTA ANTERIOR JÁ ESTÁ COMPLETA NA LISTA: pergunta_alternativas
						//VERIFICA SE A PERGUNTA CONTEM UMA IMAGEM OU NAO CASO TENHA ELE PEGA O ID DA IMAGEM DO GOOGLE SHEETS						
						if (idImagem != ""){
							if (Object.keys(dicionarioMeio).indexOf(categoriaDaPergunta) == -1) {
								dicionarioMeio[categoriaDaPergunta] = [];
								dicionarioMeio[categoriaDaPergunta][0] = label;
								dicionarioMeio[categoriaDaPergunta][1] = categoriaDaPergunta;
							}
							posicao = dicionarioMeio[categoriaDaPergunta].length -1;
							dicionarioMeio[categoriaDaPergunta][posicao+1] = imagemJSON;
							dicionarioMeio[categoriaDaPergunta][posicao+2] = {image : idImagem};
							dicionarioMeio[categoriaDaPergunta][posicao+3] = menu;
							dicionarioMeio[categoriaDaPergunta][posicao+4] = pergunta_alternativas;
							dicionarioMeio[categoriaDaPergunta][posicao+5] = label;
							dicionarioMeio[categoriaDaPergunta][posicao+6] = "NovaPergunta";
							
						} else {
							if (Object.keys(dicionarioMeio).indexOf(categoriaDaPergunta) == -1) {
								dicionarioMeio[categoriaDaPergunta] = [];
								dicionarioMeio[categoriaDaPergunta][0] = label;
								dicionarioMeio[categoriaDaPergunta][1] = categoriaDaPergunta;
							}
							posicao = dicionarioMeio[categoriaDaPergunta].length -1;
							dicionarioMeio[categoriaDaPergunta][posicao+1] = imagemJSON;
							dicionarioMeio[categoriaDaPergunta][posicao+2] = {image : "VlJlkla.png"};
							dicionarioMeio[categoriaDaPergunta][posicao+3] = menu;
							dicionarioMeio[categoriaDaPergunta][posicao+4] = pergunta_alternativas;
							dicionarioMeio[categoriaDaPergunta][posicao+5] = label;
							dicionarioMeio[categoriaDaPergunta][posicao+6] = "NovaPergunta";
						}						
						// APOS ACRESCENTAR A PERGUNTA COM A IMAGEM AS VARIAVEIS SAO ZERADAS PARA PEGAR AS NOVAS PERGUNTAS E ALTERNATIVAS
						idImagem = "";
						linha = 0; 
						eUmaCategoria = true;
						pergunta_alternativas = [] 
						pergunta_alternativas[linha] = value.title.$t; //value.title.$t é um atributo do json array que vem do gdocs 
						linha = linha + 1; 
					} else {
						pergunta_alternativas[linha] = value.title.$t; 
						linha = linha + 1; 
						if (linha != 1){
							pesoDasPerguntas  = value.content.$t.split(' ');
							pesoDasPerguntas.shift();
							pergunta_alternativas[linha] = [jsCall, { fcn: somarHabilidades, params: pesoDasPerguntas }]; 
							linha = linha + 1; 
							eUmaCategoria = false;
						} else {
							eUmaCategoria = true;
						}
					}
					imagemETipo = value.content.$t.split(','); // ARMAZENA A IMAGEM E O TIPO DE PERGUNTA EM UMA LISTA
					if (imagemETipo.length == 1 && imagemETipo[0] != "" && eUmaCategoria) { // SE O TAMANHO DA LISTA FOR DE 1 SIGNIFICA QUE NAO TEM IMAGEM APENAS O TIPO DA PERGUNTA
						categoriaDaPergunta = imagemETipo[0].substring(7).trim();
					} else if (imagemETipo.length == 2 && eUmaCategoria){ // TEM IMAGEM E TAMBEM O TIPO DA PERGUNTA
						idImagem = imagemETipo[0].substring(7).trim();
						categoriaDaPergunta = imagemETipo[1].substring(8).trim();
					}
				});	
			} 
		});
		
		//LISTA PRINCIPAL DO MEIO DO SCRIPT (CATEGORIAS, PERGUNTAS E ALTERNATIVAS)
		meio = [];
		
		meio.push(label);
		meio.push("Categorias");
		meio.push(imagemJSON);
		meio.push({image : "VlJlkla.png"});
		meio.push(menu);
		
		// lista das categorias junto com a função JUMP
		listaDeCategorias = [];
		listaDeCategorias.push("ESCOLHA UMA DAS CATEGORIAS ABAIXO: ");	
		
		// lista de perguntas e alternativas organizado com os label de cada categoria
		listaPerguntasERespostas = [];
		
		for (categoria in dicionarioMeio){
			contador = 0;
			listaDeCategorias.push(categoria);
			listaJump = [jump, categoria]
			listaDeCategorias.push(listaJump);			
			while (dicionarioMeio[categoria].length != 0){	
				if (dicionarioMeio[categoria][contador]){
					listaPerguntasERespostas.push(dicionarioMeio[categoria][contador]);
					contador+=1;
				} else {					
					listaPerguntasERespostas.pop();
					listaPerguntasERespostas.pop();
					for (i = 0; i < listaPerguntasERespostas[listaPerguntasERespostas.length-1].length; i++){
						if (Array.isArray(listaPerguntasERespostas[listaPerguntasERespostas.length-1][i])){
							listaPerguntasERespostas[listaPerguntasERespostas.length-1][i].push(jump);
							listaPerguntasERespostas[listaPerguntasERespostas.length-1][i].push("Categorias");
						}
					}
					break;
				}
			}
		}
		
		meio.push(listaDeCategorias);
		meio = meio.concat(listaPerguntasERespostas);
		
		// LISTA SCRIPT DE INICIO E FINAL:  
		inicio = [
			label, "start",
			scene, "SZZqsIt.png",
			narrator, "Bem vindo à pesquisa interativa da Fatec!.",     
			narrator, "Aqui, você vai aprender sobre suas competências, por meio de um simples jogo de perguntas e respostas.",
			narrator, "As perguntas serão divididas por categorias diferentes",
			narrator, "Ao terminar o questionário será traçado um gráfico com os seu resultado",
			narrator, "Responda com calma e atenção para que isso não afete o seu resultado final",
			narrator, "Preparado? clique para continuar e escolher uma das categorias",
		];
		
		fim = [
			label, "fim",
			imagemJSON, {image : "VlJlkla.png"},
			narrator, "O questionário acabou, clique para exibir o gráfico com o seu resultado!",
			jsCall, { fcn: mostrarGrafico, params: [] },			
		];
		
		//CONCATENA A LISTA INICIO COM A LISTA DE PERGUNTAS E RESPOSTAS E COM A LISTA FINAL
		script = inicio.concat(meio).concat(fim);
}

//FUNCAO QUE SOMA AS HABILIDADES DO ALUNO 
function somarHabilidades(equilibrioEmocional, trabalhoEquipe, resiliencia, visaoFutura, gestaoTempo, comunicacao){
	equilibrioEmocional = parseInt(equilibrioEmocional) + parseInt(sessionStorage.getItem("equilibrioEmocional"));
	sessionStorage.setItem("equilibrioEmocional",equilibrioEmocional);
	
	trabalhoEquipe = parseInt(trabalhoEquipe) + parseInt(sessionStorage.getItem("trabalhoEquipe"));
	sessionStorage.setItem("trabalhoEquipe",trabalhoEquipe);
	
	resiliencia = parseInt(resiliencia) + parseInt(sessionStorage.getItem("resiliencia"));
	sessionStorage.setItem("resiliencia",resiliencia);
	
	visaoFutura = parseInt(visaoFutura) + parseInt(sessionStorage.getItem("visaoFutura"));
	sessionStorage.setItem("visaoFutura",visaoFutura);
	
	gestaoTempo = parseInt(gestaoTempo) + parseInt(sessionStorage.getItem("gestaoTempo"));
	sessionStorage.setItem("gestaoTempo",gestaoTempo);
	
	comunicacao = parseInt(comunicacao) + parseInt(sessionStorage.getItem("comunicacao"));
	sessionStorage.setItem("comunicacao",comunicacao);

}

function mostrarGrafico(){	
	document.getElementById("novelDiv").style.display = 'none';
	document.getElementById("radarChart").style.display = 'block';
	postarDados(); 
	
	var radarData = {
		labels : ["Equilibrio Emocional","Trabalho em Equipe","Resiliência","Visão Futura","Gestão do Tempo","Comunicação"],
		datasets : [
			{
				fillColor: "rgba(63,169,245,.1)",
				strokeColor: "rgba(63,169,245,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				data : [sessionStorage.getItem("equilibrioEmocional"),sessionStorage.getItem("trabalhoEquipe"),sessionStorage.getItem("resiliencia"),sessionStorage.getItem("visaoFutura"),sessionStorage.getItem("gestaoTempo"),sessionStorage.getItem("comunicacao")]
			}
		]
	}

	//Chart
	var ctx2 = document.getElementById("radarChart").getContext("2d");
	var myNewChart = new Chart(ctx2).Radar(radarData);
	
	var options = {
		
		}	
	new Chart(ctx2).Radar(radarData,options);

}

function setarGrafico(){
	sessionStorage.setItem("equilibrioEmocional",0);
	sessionStorage.setItem("trabalhoEquipe",0);
	sessionStorage.setItem("resiliencia",0);
	sessionStorage.setItem("visaoFutura",0);
	sessionStorage.setItem("gestaoTempo",0);
	sessionStorage.setItem("comunicacao",0);
}

function postarDados(){
	//https://docs.google.com/forms/d/1xmrzmlj5g8TiiA5m3sSgRkAEX0cKnb7Hr-0Z7RL73hg/viewform
	//https://docs.google.com/spreadsheets/d/1_UycrVzV2rUJlUhYKf-nY8_Xgxu0lBdleRDZqruPTvA/edit#gid=559348360
	nome = sessionStorage.getItem("nome");
	curso = sessionStorage.getItem("curso");
	equilibrio = sessionStorage.getItem("equilibrioEmocional");
	equipe = sessionStorage.getItem("trabalhoEquipe");
	resiliencia = sessionStorage.getItem("resiliencia");
	futura = sessionStorage.getItem("visaoFutura");
	tempo = sessionStorage.getItem("gestaoTempo");
	comunicacao = sessionStorage.getItem("comunicacao");
	email = sessionStorage.getItem("email");

	$.post("https://docs.google.com/forms/d/1xmrzmlj5g8TiiA5m3sSgRkAEX0cKnb7Hr-0Z7RL73hg/formResponse", {'entry.1031801980': nome, 'entry.1117932187': curso, 'entry.133099883': equilibrio, 'entry.1504018378': equipe, 'entry.1011797603': resiliencia, 'entry.1368032476': futura, 'entry.943551081': tempo, 'entry.1099986603': comunicacao, 'entry.1214238962': email, 'entry.1420760101': 0}, function(data){
		
	});
}
