import React, { useEffect } from 'react';

function VoiceflowChatWidget() {
  useEffect(() => {
    const loadChatWidget = () => {
      const v = document.createElement('script');
      const s = document.getElementsByTagName('script')[0];

      v.onload = () => {
        window.voiceflow.chat.load({
          verify: { projectID: '653d59728020960007927f64' },
          url: 'https://general-runtime.voiceflow.com',
          versionID: 'production'
        });
      };

      v.src = 'https://cdn.voiceflow.com/widget/bundle.mjs';
      v.type = 'text/javascript';
      s.parentNode.insertBefore(v, s);
    };

    loadChatWidget();
  }, []);

  return <div></div>; // You can return any JSX content you want
}

export default VoiceflowChatWidget;
