import React, { useState } from 'react';
import './App.scss';
import ExportButton from "./ExportButton";
import RendaringArea from "./RendaringArea";

const App: React.FC = () => {
  const [inputText, setInputText] = useState("");

  return (
    <div className="App">
      <header className="App-header has-background-warning">
        <div className="title is-3 has-text-white-ter has-text-weight-bold">PlantUML Viewer</div>
      </header>
      <div className="App-main">
        <div className="input-area">
          <textarea
            className="box"
            value={inputText}
            placeholder="Bob -> Alice: Hello"
            onChange={(event) => setInputText(event.target.value)}
          />
        </div>
        <div className="display-area">
          <div className="button-area">
            <ExportButton plantUml={inputText} label="PDF" type="pdf"></ExportButton>
          </div>
          <div className="box preview-area">
            <RendaringArea plantUml={inputText}></RendaringArea>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
