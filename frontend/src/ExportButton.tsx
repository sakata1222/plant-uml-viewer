import React from 'react';
import './ExportButton.scss';

interface IProps {
    readonly plantUml: string;
    readonly label: string;
    readonly type: string;
}

const ExportButton: React.FC<IProps> = (props: IProps) => {
    const openFile = (event: any) => {
        fetch(props.type, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
                plantUml: "@startuml\n" + props.plantUml + "\n@enduml"
            })
        })
            .then(response => response.json())
            .then(json => {
                window.open(json.rawDataUrl)
            })
    };
    return (
        <button
            className="button is-warning is-rounded"
            type="button"
            onClick={openFile}
        >
            {props.label}
        </button>
    );
}

export default ExportButton;