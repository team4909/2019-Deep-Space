let             SERVER = "10.49.9.11";
let             janus;

// Initialize Janus
Janus.init(
  {
    debug        : true,
    dependencies : Janus.useDefaultDependencies(),
    callback     : () =>
      {
        console.log("Janus is initialized");
        
        // Create the one single session used for all four cameras
        createSession();
      }
  });

/**
 * Create a session with Janus. Upon successful connection, connect to each of
 * the cameras.
 */
function createSession()
{
  janus = new Janus(
    {
      server : "http://" + SERVER + ":8088/janus",
      
      success : () =>
        {
          console.log("Janus session created");
          connect(1);
          connect(2);
          connect(3);
          connect(4);
        },
      
      error : (cause) =>
        {
          console.log(`Janus session error: ${cause}`);
        },
      
      destroyed : () =>
        {
          console.log("Janus session destroyed");
        }
    });
}

/**
 * Connect to a Janus camera stream.
 *
 * @param camera {Number}
 *   The number of the camera whose stream is requested
 */
function connect(camera)
{
  let             id;
  let             pluginHandle;
  let             bitrateTimer;
  
  // Stream IDs and port numbers are 8010, 8020, 8030, and 8040 for the four
  // cameras. Generate those IDs based on the camera number.
  id = (800 + camera) * 10;
  
  // Attach to streaming plugin, using the previously created janus instance
  console.log("Attaching to plugin...");
  janus.attach(
    {
      plugin: "janus.plugin.streaming",

      success: (handle) =>
        {
          // Save the plugin handle
          pluginHandle = handle;

          console.log(`Plugin attached, handle=${pluginHandle}`);

          // Periodically, show the current bitrate and resolution
          bitrateTimer = setInterval(
            () =>
              {
                let             width;
                let             height;
                let             bitrate;
                let             videoElem;
                let             bitrateElem;
                let             resolutionElem;

                bitrate = pluginHandle.getBitrate();
                videoElem = document.getElementById("video" + camera);
                resolutionElem = 
                  document.getElementById("resolution" + camera);

                // Display updated bitrate, if supported
                bitrateElem = document.getElementById("bitrate" + camera);
                bitrateElem.textContent = bitrate + "";

                width = videoElem.videoWidth;
                height = videoElem.videoHeight;
                resolutionElem.textContent = width + "x" + height;
              },
            1000);

          // We have the id already. Request to watch it.
          pluginHandle.send(
            {
              message :
              {
                request : "watch",
                id      : id
              }
            });

        },
      error: (cause) =>
        {
          console.log(`Plugin attach failed: ${cause}`);
        },

      webrtcState : (bActive) =>
        {
          let             state = bActive ? "active" : "inactive";
          console.log(`WebRTC has gone ${state}`);
        },

      mediaState : (type, bActive) =>
        {
          let             state = bActive ? "active" : "inactive";
          console.debug(`mediaState ${state} detected on ${type}`);
        },

      slowLink : (bUplink) =>
        {
          let             state = bUplink ? "uplink" : "downlink";
          console.log(`slow link detected on ${state}`);
        },

      onlocalstream: (stream) =>
        {
          console.log("attaching to local stream (irrelevant here)");
        },

      onremotestream: (stream) =>
        {
          console.log("attaching to remote stream");
          document.getElementById("video" + camera).srcObject = stream;
        },

      onmessage: (msg, jsep) =>
        {
          if (msg.error)
          {
            console.log(`Stream error: ${msg.error}`);
          }

          if (jsep)
          {
            // We've received an offer. Answer.
            pluginHandle.createAnswer(
              {
                jsep : jsep,

                media :
                {
                  // We want receive-only video, no audio
                  audioRecv : false,
                  videoRecv : true,
                  audioSend : false,
                  videoSend : false
                },

                success : function(jsep)
                {
                  pluginHandle.send(
                    {
                      message :
                      {
                        request : "start"
                      },
                      jsep : jsep
                    });
                }.bind(this),

                error : function(error)
                {
                  console.error(`Create answer error: ${error}`);
                }.bind(this)
              });
          }
        },

      oncleanup: () =>
        {
          // PeerConnection with the plugin closed, clean the UI
          // The plugin handle is still valid so we can create a new one
          console.log("Peer connection with plugin closed");
        },

      detached: () =>
        {
          // Connection with the plugin closed, get rid of its features
          // The plugin handle is not valid anymore
          console.log("Plugin detached");
          if (bitrateTimer)
          {
            clearInterval(bitrateTimer);
            bitrateTimer = null;
          }
        }
    });  
}
