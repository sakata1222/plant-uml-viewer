import React, { useState, useEffect, useRef } from 'react';
import './RendaringArea.scss';
import { debounce } from "lodash";

interface IProps {
  readonly plantUml: string;
}

const RendaringArea: React.FC<IProps> = (props: IProps) => {
  const [isLoading, setLoading] = useState(true);
  const [svgPath, setSvgPath] = useState("");
  // hack to use debounce in a React function component
  const umlTxt = useRef("");
  umlTxt.current = props.plantUml;
  const rendaring = useRef(debounce(() => {
    setLoading(true)
    fetch("svg", {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify({
        plantUml: "@startuml\n" + umlTxt.current + "\n@enduml"
      })
    })
      .then(resposne => resposne.json())
      .then(json => {
        setSvgPath(json.rawDataPath)
        setLoading(false)
      })
  }, 1000)).current;

  useEffect(rendaring, [props.plantUml]);

  if (isLoading) {
    return <div></div>
  } else {
    return <img src={svgPath} alt="Unable to display SVG" />
  }
}

export default RendaringArea;
