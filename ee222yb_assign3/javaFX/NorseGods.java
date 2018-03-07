package ee222yb_assign3.javaFX;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class NorseGods extends Application {

	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
		ArrayList<God> gods = getGods();
		
		// List 
		ListView<String> godsList = new ListView<>();
		for(God god : gods)
			godsList.getItems().add(god.name);
		godsList.setPrefWidth(175);
		
		// Main box content
		Text godName = new Text();
		godName.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		Text godRace = new Text();
		godRace.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
		Text info = new Text("Select a god to view detailed information");
		
		TextFlow textFlow = new TextFlow(godName, godRace, info);
		textFlow.setPrefWidth(400);
		textFlow.setMaxWidth(450);
		ScrollPane sPane = new ScrollPane(textFlow);
		sPane.setPadding(new Insets(5));
		sPane.setFitToWidth(true);
		
		//Layout
		Label title = new Label("Norse Gods and other Beings");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		HBox main = new HBox(godsList, sPane);
		main.setSpacing(10);
		VBox root = new VBox(title, main);
		root.setPadding(new Insets(10));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Norse Gods");
		stage.setHeight(400);
		stage.setResizable(false);
		stage.show();
		
		// on Select
		godsList.getSelectionModel().selectedIndexProperty().addListener(ov -> {
			int index = godsList.getSelectionModel().getSelectedIndex();
			God thisGod = gods.get(index);
			godName.setText(thisGod.name + "\n");
			godRace.setText(thisGod.race + "\n\n");
			info.setText(thisGod.description);
		});
	}
	
	
	private ArrayList<God> getGods() {
		ArrayList<God> gods = new ArrayList<>();
		gods.add(new God("Odin", "Aesir", "Odin is a prominently mentioned god throughout the recorded history of the Germanic peoples, from the Roman occupation of regions of Germania through the tribal expansions of the Migration Period and the Viking Age. In the modern period, Odin continued to be acknowledged in the rural folklore of Germanic Europe. References to Odin appear in place names throughout regions historically inhabited by the ancient Germanic peoples, and the day of the week Wednesday bears his name in many Germanic languages, including English."));
		gods.add(new God("Thor", "Aesir", "Thor is a prominently mentioned god throughout the recorded history of the Germanic peoples, from the Roman occupation of regions of Germania, to the tribal expansions of the Migration Period, to his high popularity during the Viking Age, when, in the face of the process of the Christianization of Scandinavia, emblems of his hammer, Mj�lnir, were worn and Norse pagan personal names containing the name of the god bear witness to his popularity."));
		gods.add(new God("Loki", "Aesir", "Loki's relation with the gods varies by source; Loki sometimes assists the gods and sometimes behaves in a malicious manner towards them. Loki is a shape shifter and in separate incidents he appears in the form of a salmon, a mare, a fly, and possibly an elderly woman named ��kk (Old Norse 'thanks'). Loki's positive relations with the gods end with his role in engineering the death of the god Baldr and Loki is eventually bound by V�li with the entrails of one of his sons. In both the Poetic Edda and the Prose Edda, the goddess Ska�i is responsible for placing a serpent above him while he is bound. The serpent drips venom from above him that Sigyn collects into a bowl; however, she must empty the bowl when it is full, and the venom that drips in the meantime causes Loki to writhe in pain, thereby causing earthquakes. With the onset of Ragnar�k, Loki is foretold to slip free from his bonds and to fight against the gods among the forces of the j�tnar, at which time he will encounter the god Heimdallr and the two will slay each other."));
		gods.add(new God("Baldr", "Aesir", "Apart from this description, Baldr is known primarily for the story of his death. His death is seen as the first in the chain of events which will ultimately lead to the destruction of the gods at Ragnar�k. Baldr will be reborn in the new world, according to V�lusp�."));
		gods.add(new God("Freyr", "Vanir", "In the Icelandic books the Poetic Edda and the Prose Edda, Freyr is presented as one of the Vanir, the son of the sea god Nj�r�r, as well as the twin brother of the goddess Freyja. The gods gave him �lfheimr, the realm of the Elves, as a teething present. He rides the shining dwarf-made boar Gullinbursti and possesses the ship Sk��bla�nir which always has a favorable breeze and can be folded together and carried in a pouch when it is not being used. He has the servants Sk�rnir, Byggvir and Beyla."));
		gods.add(new God("Freyja","Vanir", "Freyja rules over her heavenly field F�lkvangr and there receives half of those that die in battle, whereas the other half go to the god Odin's hall, Valhalla. Within F�lkvangr lies her hall, Sessr�mnir. Freyja assists other deities by allowing them to use her feathered cloak, is invoked in matters of fertility and love, and is frequently sought after by powerful j�tnar who wish to make her their wife. Freyja's husband, the god ��r, is frequently absent. She cries tears of red gold for him, and searches for him under assumed names. Freyja has numerous names, including Gefn, H�rn, Mard�ll, S�r, Valfreyja, and Vanad�s."));
		gods.add(new God("Heimdallr", "Aesir", "In Norse mythology, Heimdallr is a god who possesses the resounding horn Gjallarhorn, owns the golden-maned horse Gulltoppr, has gold teeth, and is the son of Nine Mothers, grandson of Aegir and great grandson of Fornjotr. Heimdallr is attested as possessing foreknowledge, keen eyesight and hearing, and keeps watch for the onset of Ragnar�k while drinking fine mead in his dwelling Himinbj�rg, located where the burning rainbow bridge Bifr�st meets heaven. Heimdallr is said to be the originator of social classes among humanity and once regained Freyja's treasured possession Br�singamen while doing battle in the shape of a seal with Loki. Heimdallr and Loki are foretold to kill one another during the events of Ragnar�k. Heimdallr is additionally referred to as Rig, Hallinski�i, Gullintanni, and Vindl�r or Vindhl�r."
				+ "\n\nHeimdallr is attested in the Poetic Edda, compiled in the 13th century from earlier traditional material; in the Prose Edda and Heimskringla, both written in the 13th century by Snorri Sturluson; in the poetry of skalds; and on an Old Norse runic inscription found in England. Two lines of an otherwise lost poem about the god, Heimdalargaldr, survive. Due to the problematic and enigmatic nature of these attestations, scholars have produced various theories about the nature of the god, including his apparent relation to rams, that he may be a personification of or connected to the world tree Yggdrasil, and potential Indo-European cognates. "));
		gods.add(new God("Bragi", "Aesir", "That Bragi is Odin's son is clearly mentioned only here and in some versions of a list of the sons of Odin (see Sons of Odin). But \"wish-son\" in stanza 16 of the Lokasenna could mean \"Odin's son\" and is translated by Hollander as Odin's kin. Bragi's mother is possibly the giantess Gunnlod. If Bragi's mother is Frigg, then Frigg is somewhat dismissive of Bragi in the Lokasenna in stanza 27 when Frigg complains that if she had a son in �gir's hall as brave as Baldr then Loki would have to fight for his life."
				+ "\n\nIn that poem Bragi at first forbids Loki to enter the hall but is overruled by Odin. Loki then gives a greeting to all gods and goddesses who are in the hall save to Bragi. Bragi generously offers his sword, horse, and an arm ring as peace gift but Loki only responds by accusing Bragi of cowardice, of being the most afraid to fight of any of the �sir and Elves within the hall. Bragi responds that if they were outside the hall, he would have Loki's head, but Loki only repeats the accusation. When Bragi's wife I�unn attempts to calm Bragi, Loki accuses her of embracing her brother's slayer, a reference to matters that have not survived. It may be that Bragi had slain I�unn's brother."));
		gods.add(new God("Ymir", "Giant", "In Norse mythology, Ymir, Aurgelmir, Brimir, or Bl�inn is the ancestor of all j�tnar. Ymir is attested in the Poetic Edda, compiled in the 13th century from earlier traditional material, in the Prose Edda, written by Snorri Sturluson in the 13th century, and in the poetry of skalds. Taken together, several stanzas from four poems collected in the Poetic Edda refer to Ymir as a primeval being who was born from venom that dripped from the icy rivers �liv�gar and lived in the grassless void of Ginnungagap. Ymir birthed a male and female from the pits of his arms, and his legs together begat a six-headed being. The gods Odin, Vili and V� fashioned the Earth (elsewhere personified as a goddess; J�r�) from his flesh, from his blood the ocean, from his bones the mountains, from his hair the trees, from his brains the clouds, from his skull the heavens, and from his eyebrows the middle realm in which mankind lives, Midgard. In addition, one stanza relates that the dwarfs were given life by the gods from Ymir's flesh and blood (or the Earth and sea)."));
		return gods;
	}

	class God {
		private String name;
		private String race;
		private String description;
		
		public God(String newName, String newRace, String newDescription) {
			name = newName;
			race = newRace;
			description = newDescription;
		}
	}
	
}
