package com.pany.fishy

import org.junit.Assert.assertTrue
import org.junit.Test

class QuizTest {

//  @Test
//  fun loadQuestions() {
//    val quiz = Quiz()
//    assertTrue(quiz.loadQuestions().isNotEmpty())
//  }
//
//  @Test
//  fun loadProgress() {
//    val quiz = Quiz()
//    val progress = quiz.loadProgress()
//    assertTrue(progress.save.isNotEmpty())
//    assertTrue(progress.correct.isNotEmpty())
//    assertTrue(progress.wrong.isNotEmpty())
//    assertTrue(progress.new.isEmpty())
//  }
// ############# Unit test question parser #############
//  public void name() throws IOException {
//    String startRegex = "(I|V){1,3} \\d \\d{1,3} \\d{1,3}";
//    String keywordsRegex = "(?=Zur |Als |In |Im |Oberhalb |An |Am |Ein |Eine |Sie |Der |Die |Das |Er |Durch |Bei |Auf |Nur|Wegen |Von |Nein|Ja)";
//    final String pdFasString = TestHelper.getString(IOUtils.toByteArray(fragenKatalogText.getInputStream()));
//    final List<String> lines = Arrays.asList(pdFasString.split(startRegex));
//
//    int spaces = 0;
//    int dots = 0;
//    int keywords = 0;
//    int oneline = 0;
//
//    StringBuilder builder = new StringBuilder();
//    for (int i = 0; i < lines.size(); i++) {
//      String line = lines.get(i).replaceAll("\\n", " ");
//      if (StringUtils.isNotBlank(line)) {
//        String separator;
//        if (line.contains("?")) {
//          separator = "\\?";
//        } else {
//          separator = line.contains(":") ? "\\:" : "\\.{3}";
//        }
//        String[] parts = line.split(separator);
//        builder.append(parts[0]);
//        builder.append(separator.substring(1));
//        builder.append("\n");
//
//        String answerPart = StringUtils.trim(parts[1]).replaceAll(", ", ",");
//        String[] answers;
//        if (StringUtils.countMatches(answerPart, " ") == 2) {
//          answers = answerPart.split(" ");
//          builder.append(answers[0]);
//          builder.append("\n");
//          builder.append(answers[1]);
//          builder.append("\n");
//          builder.append(answers[2]);
//          builder.append("\n");
//          spaces++;
//        } else if (StringUtils.countMatches(answerPart, ".") == 3) {
//          answers = answerPart.split("\\.");
//          builder.append(answers[0]);
//          builder.append(".\n");
//          builder.append(answers[1]);
//          builder.append(".\n");
//          builder.append(answers[2]);
//          builder.append(".\n");
//          dots++;
//        } else if (countMatches(answerPart, keywordsRegex) == 3) {
//          answers = answerPart.split(keywordsRegex);
//          builder.append(answers[0]);
//          builder.append("\n");
//          builder.append(answers[1]);
//          builder.append("\n");
//          builder.append(answers[2]);
//          builder.append("\n");
//          keywords++;
//        } else {
//          builder.append(answerPart);
//          builder.append("\n\n\n");
//          oneline++;
//        }
//      }
//    }
//
//    Files.write(Paths.get(fragenText.getURI()), builder.toString().getBytes());
//
//    System.out.println("Spaces: " + spaces);
//    System.out.println("Dots: " + dots);
//    System.out.println("Keywords: " + keywords);
//    System.out.println("One lines: " + oneline);
//
//    assertNotNull(lines);
//  }
}
